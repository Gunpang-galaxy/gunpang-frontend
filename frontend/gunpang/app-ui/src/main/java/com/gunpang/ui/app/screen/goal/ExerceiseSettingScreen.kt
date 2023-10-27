package com.gunpang.ui.app.screen.goal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gunpang.common.code.DayCode
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.app.common.CommonTextField
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Navy200
import com.gunpang.ui.theme.Shapes
import com.gunpang.ui.theme.gmarketsansTypo

@Composable
fun DayButton(
    day: DayCode, // 사용자가 클릭한 요일
    selected: Boolean = false, // 사용자가 선택한 요일인지
    onDayChange: (DayCode) -> Unit,
) {
    Button(
        onClick = {
            onDayChange(day)
        },
        modifier = Modifier.size(35.dp),
        shape = Shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Navy200
            else Color.Transparent
        ),
        border = BorderStroke(1.dp, Navy200),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = day.kor,
            color = Gray900,
            style = gmarketsansTypo.titleMedium,
        )
    }
}

@Composable
fun ExerciseDay(
    defaultSelectedDay: Int = 0, // 이전 목표 설정값
    onSelectedDayChange: (Int) -> Unit,
) {
    var bitSelectedDay by remember { mutableIntStateOf(defaultSelectedDay) }
    var selectedDay by remember { mutableStateOf<Set<DayCode?>> (setOf()) }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.width(300.dp)
    ) {
        for (day in DayCode.values()) {
            DayButton(
                day = day,
                onDayChange = {
                    if (selectedDay.contains(it)) { // 이미 선택한 날짜
                        selectedDay = selectedDay.minus(it)
                        bitSelectedDay = bitSelectedDay xor day.bitCount
                    } else { // 아직 선택하지 않은 날짜
                        selectedDay = selectedDay.plus(it)
                        bitSelectedDay = bitSelectedDay xor day.bitCount
                    }
                    onSelectedDayChange(bitSelectedDay)
                },
                selected = selectedDay.contains(day)
            )
        }
    }
}

@Composable
fun ExerciseTime(
    defaultMinute: String = "", // 이전 목표 설정값
    onMinuteChange: (String) -> Unit,
) {
    var minute by remember { mutableStateOf(defaultMinute) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "회당",
            style = gmarketsansTypo.titleLarge
        )
        CommonTextField(
            defaultValue = minute,
            onValueChange = {
                minute = it
                onMinuteChange(it)
            },
            leftPadding = 30,
            rightPadding = 10
        )
        Text(
            text = "분",
            style = gmarketsansTypo.titleLarge
        )
    }
}

// 목표한 운동 시간이 유효한지
fun exerciseAvailable(minute: String): Boolean {
    try {
        return minute.toInt() in 0..600
    } catch (e: Exception) {
        return false
    }
}

@Composable
fun ExerciseGoal() {
    var selectedDay by remember { mutableIntStateOf(0) } // 선택한 날짜
    var minute by remember { mutableStateOf("") } // 입력한 시간

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "목표 운동 시간을\n입력해 주세요",
            style = gmarketsansTypo.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 50.dp)
        )
        ExerciseDay(
            defaultSelectedDay = selectedDay,
            onSelectedDayChange = {
                selectedDay = it
            }
        )
        Spacer( // 시간 입력 창과 버튼 사이 간격
            modifier = Modifier.size(30.dp)
        )
        ExerciseTime(
            defaultMinute = minute,
            onMinuteChange = {
                minute = it
            }
        )
        Spacer( // 시간 입력 창과 버튼 사이 간격
            modifier = Modifier.size(70.dp)
        )
        CommonButton(
            text = "입력",
            enabled = (selectedDay > 0 && exerciseAvailable(minute)),
        )
    }
}