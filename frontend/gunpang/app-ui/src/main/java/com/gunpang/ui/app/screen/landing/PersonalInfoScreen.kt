package com.gunpang.ui.app.screen.landing

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gunpang.common.code.GenderCode
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.theme.Gray200
import com.gunpang.ui.theme.Gray500
import com.gunpang.ui.theme.Gray800
import com.gunpang.ui.theme.Navy200
import com.gunpang.ui.theme.Shapes
import com.gunpang.ui.theme.galmuriTyop
import com.gunpang.ui.theme.gmarketsansTypo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeightInfo(onHeightChange: (String) -> Unit) {
    var height by remember { mutableStateOf("") }

    Row {
        Text(
            text = "키",
            style = galmuriTyop.titleMedium
        )
        TextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            value = height,
            onValueChange = {
                height = it
                onHeightChange(it)
            },
            modifier = Modifier
                .width(150.dp)
                .padding(start = 30.dp, end = 10.dp)
                .border(color = Gray500, width = 1.dp, shape = Shapes.medium),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Gray800,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true, // enter 입력 불가
            textStyle = gmarketsansTypo.titleMedium
        )
        Text(
            text = "cm",
            style = galmuriTyop.titleMedium
        )
    }
}

@Composable
fun GenderInfo(onGenderChange: (GenderCode?) -> Unit) {
    var selectedGender by remember { mutableStateOf<GenderCode?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            // 남성 선택 버튼
            onClick = {
                selectedGender = GenderCode.Male
                onGenderChange(selectedGender)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp),
            shape = Shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedGender == GenderCode.Male) Navy200
                else Gray200
            ),
        ) {
            Text(
                text = GenderCode.Male.kor,
                color = Gray800,
                style = gmarketsansTypo.titleMedium
            )
        }
        Button(
            // 여성 선택 버튼
            onClick = {
                selectedGender = GenderCode.Female
                onGenderChange(selectedGender)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp),
            shape = Shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedGender == GenderCode.Female) Navy200
                else Gray200
            ),
        ) {
            Text(
                text = GenderCode.Female.kor,
                color = Gray800,
                style = gmarketsansTypo.titleMedium
            )
        }
    }
}

@Composable
fun PersonalInfo() {
    var isHeightInfoFilled by remember { mutableStateOf(false) }
    var isGenderInfoFilled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "신체 정보",
            style = galmuriTyop.titleMedium,
            modifier = Modifier.padding(bottom = 50.dp),
            textAlign = TextAlign.Center
        )
        HeightInfo() {
            isHeightInfoFilled = it.isNotEmpty()
        }
        GenderInfo() {
            isGenderInfoFilled = it != null
        }
        Spacer( // 성별 선택 버튼과 입력 완료 버튼의 간격
            modifier = Modifier.height(100.dp)
        )
        CommonButton(
            text = "입력 완료",
            enabled = isHeightInfoFilled && isGenderInfoFilled,
        )
    }
}
