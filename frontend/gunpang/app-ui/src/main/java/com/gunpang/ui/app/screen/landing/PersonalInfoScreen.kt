package com.gunpang.ui.app.screen.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gunpang.common.code.GenderCode
import com.gunpang.common.navigation.AppNavItem
import com.gunpang.domain.app.landing.PersonalInfoViewModel
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.app.common.CommonTextField
import com.gunpang.ui.theme.Gray200
import com.gunpang.ui.theme.Gray800
import com.gunpang.ui.theme.Navy200
import com.gunpang.ui.theme.Shapes
import com.gunpang.ui.theme.gmarketsansTypo

@Composable
fun AgeInfo(onAgeChange: (String) -> Unit) {
    var age by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "나이",
            style = gmarketsansTypo.titleLarge
        )
        CommonTextField(
            onValueChange = {
                age = it
                onAgeChange(it)
            },
            leftPadding = 30,
            rightPadding = 10
        )
        Text(
            text = "세",
            style = gmarketsansTypo.titleLarge
        )
    }
}

@Composable
fun HeightInfo(onHeightChange: (String) -> Unit) {
    var height by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "키",
            style = gmarketsansTypo.titleLarge
        )
        CommonTextField(
            onValueChange = {
                height = it
                onHeightChange(it)
            },
            leftPadding = 30,
            rightPadding = 10
        )
        Text(
            text = "cm",
            style = gmarketsansTypo.titleLarge
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
                selectedGender = GenderCode.MALE
                onGenderChange(selectedGender)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp),
            shape = Shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedGender == GenderCode.MALE) Navy200
                else Gray200
            ),
        ) {
            Text(
                text = GenderCode.MALE.kor,
                color = Gray800,
                style = gmarketsansTypo.titleMedium
            )
        }
        Button(
            // 여성 선택 버튼
            onClick = {
                selectedGender = GenderCode.FEMALE
                onGenderChange(selectedGender)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp),
            shape = Shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedGender == GenderCode.FEMALE) Navy200
                else Gray200
            ),
        ) {
            Text(
                text = GenderCode.FEMALE.kor,
                color = Gray800,
                style = gmarketsansTypo.titleMedium
            )
        }
    }
}

@Composable
fun PersonalInfo(
    navController: NavController,
    personalInfoViewModel: PersonalInfoViewModel = viewModel()
) {
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var isAgeInfoFilled by remember { mutableStateOf(false) }
    var isHeightInfoFilled by remember { mutableStateOf(false) }
    var isGenderInfoFilled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 등록된 개인 정보가 있다면 메인화면으로 이동
        if (personalInfoViewModel.hasPersonalInfo()) {
            navController.navigate(AppNavItem.MainScreen.routeName)
        } else {
            Text(
                text = "신체 정보",
                style = gmarketsansTypo.headlineLarge,
                modifier = Modifier.padding(bottom = 50.dp),
                textAlign = TextAlign.Center
            )
            AgeInfo() {
                age = it
                isAgeInfoFilled = it.isNotEmpty()
            }
            Spacer( // 나이, 키 입력 사이 구간
                modifier = Modifier.height(100.dp)
            )
            HeightInfo() {
                height = it
                isHeightInfoFilled = it.isNotEmpty()
            }
            GenderInfo() {
                gender = it!!.engUppercase
                isGenderInfoFilled = it != null
            }
            Spacer( // 성별 선택 버튼과 입력 완료 버튼의 간격
                modifier = Modifier.height(100.dp)
            )
            CommonButton(
                text = "입력 완료",
                enabled = isHeightInfoFilled && isGenderInfoFilled,
                onClick = {
                    personalInfoViewModel.registerPersonalInfo(age, height, gender)
                    navController.navigate(AppNavItem.MainScreen.routeName)
                }
            )
        }
    }
}
