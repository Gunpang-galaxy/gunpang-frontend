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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.gunpang.ui.theme.Gray500
import com.gunpang.ui.theme.Gray800
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Navy200
import com.gunpang.ui.theme.Shapes
import com.gunpang.ui.theme.gmarketsansTypo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgeInfo(onAgeChange: (String) -> Unit) {
    val years = (1950..2023).map { it.toString() }
    var birthYear by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "출생년도",
            style = gmarketsansTypo.titleLarge,
            color = Gray900
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                value = birthYear,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .width(150.dp)
                    .padding(start = 20.dp)
                    .border(color = Gray500, width = 1.dp, shape = Shapes.medium),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Gray800,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = Gray900
                ),
                singleLine = true, // enter 입력 불가
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontFamily = gmarketsansTypo.titleMedium.fontFamily
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                years.forEach { year ->
                    DropdownMenuItem(
                        text = { Text(year) },
                        onClick = {
                            birthYear = year
                            onAgeChange(year)
                            expanded = false
                        },
                        modifier = Modifier.width(100.dp),
                        colors = MenuDefaults.itemColors(
                            textColor = Gray900,
                        )
                    )
                }
            }
        }
    }
}

// 키 정보가 int 형태로 유지되도록 하기
fun parseHeight(height: String): String {
    // 소숫점 제거
    if (height.contains(".")) {
        return height.substring(0, height.indexOf("."))
    }

    // int로 parseing할 수 없는 경우 빈 문자열로
    try {
        Integer.parseInt(height)
    } catch (e: NumberFormatException) {
        return ""
    }
    return height
}

@Composable
fun HeightInfo(onHeightChange: (String) -> Unit) {
    var height by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "키",
            style = gmarketsansTypo.titleLarge,
            color = Gray900
        )
        CommonTextField(
            onValueChange = {
                height = parseHeight(it!!)
                onHeightChange(parseHeight(it))
            },
            leftPadding = 30,
            rightPadding = 10
        )
        Text(
            text = "cm",
            style = gmarketsansTypo.titleLarge,
            color = Gray900
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
    var birthYear by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var isBirthYearInfoFilled by remember { mutableStateOf(false) }
    var isHeightInfoFilled by remember { mutableStateOf(false) }
    var isGenderInfoFilled by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        if (personalInfoViewModel.hasPersonalInfo()) {
            navController.navigate(AppNavItem.MainScreen.routeName)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 등록된 개인 정보가 있다면 메인화면으로 이동
        Text(
            text = "신체 정보",
            style = gmarketsansTypo.headlineLarge,
            color = Gray900,
            modifier = Modifier.padding(bottom = 50.dp),
            textAlign = TextAlign.Center
        )
        AgeInfo {
            birthYear = it
            isBirthYearInfoFilled = it.isNotEmpty()
        }
        Spacer( // 나이, 키 입력 사이 구간
            modifier = Modifier.height(30.dp)
        )
        HeightInfo {
            height = it
            isHeightInfoFilled = it.isNotEmpty()
        }
        GenderInfo {
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
                personalInfoViewModel.registerPersonalInfo(birthYear, height, gender) // 회원가입 + 개인 정보 등록
//                navController.navigate(AppNavItem.MainScreen.routeName)
                navController.navigate(AppNavItem.AvatarEgg.routeName)
            }
        )
    }
}
