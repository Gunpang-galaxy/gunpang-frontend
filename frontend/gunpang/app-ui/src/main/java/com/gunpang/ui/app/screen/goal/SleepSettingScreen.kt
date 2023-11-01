package com.gunpang.ui.app.screen.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.app.common.CommonTextField
import com.gunpang.ui.theme.gmarketsansTypo


@Composable
fun SleepTime(defaultHour: String, defaultMinute: String, onHourChange: (String) -> Unit, onMinuteChange: (String) -> Unit) {
    var hour by remember { mutableStateOf(defaultHour) }
    var minute by remember { mutableStateOf(defaultMinute) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CommonTextField(
            defaultValue = hour,
            onValueChange = {
                hour = it
                onHourChange(it)
            },
            width = 100,
            rightPadding = 10,
        )
        Text(
            text = ":",
            style = gmarketsansTypo.headlineLarge,
        )
        CommonTextField(
            defaultValue = minute,
            onValueChange = {
                minute = it
                onMinuteChange(it)
            },
            width = 100,
            leftPadding = 10,
        )
    }
}

// 입력 받은 시간이 유효한지 확인
fun timeAvailable(hour: String, minute: String ): Boolean {
    try {
        return hour.toInt() in 0..23 && minute.toInt() in 0..59
    } catch (e: Exception) {
        return false
    }
}

@Composable
fun SleepGoal(
    navController: NavController
) {
    var startHour by remember { mutableStateOf("") }
    var startMinute by remember { mutableStateOf("") }
    var endHour by remember { mutableStateOf("") }
    var endMinute by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "목표 수면시간을\n입력해 주세요",
            style = gmarketsansTypo.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 70.dp)
        )
        SleepTime( // 취침 시간 입력
            defaultHour = startHour,
            defaultMinute = startMinute,
            onHourChange = { startHour = it },
            onMinuteChange = { startMinute = it }
        )
        Text(
            text = "~",
            style = gmarketsansTypo.headlineLarge,
        )
        SleepTime( // 기상 시간 입력
            defaultHour = endHour,
            defaultMinute = endMinute,
            onHourChange = { endHour = it },
            onMinuteChange = { endMinute = it }
        )
        Spacer( // 시간 입력 창과 버튼 사이 간격
            modifier = Modifier.size(100.dp)
        )
        CommonButton(
            text = "입력",
            enabled = (timeAvailable(startHour, startMinute) && timeAvailable(endHour, endMinute)),
            onClick = {
                navController.navigate("exerciseGoal")
            }
        )
    }
}