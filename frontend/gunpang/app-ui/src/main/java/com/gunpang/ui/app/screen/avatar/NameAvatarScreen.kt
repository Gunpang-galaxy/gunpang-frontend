package com.gunpang.ui.app.screen.avatar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gunpang.common.R
import com.gunpang.common.code.AvatarCode
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.app.common.CommonTextField
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansTypo
import kotlin.random.Random

@Composable
fun NewAvatar(
    onNameChange: (String) -> Unit
) {
    // 아바타 랜덤 선택
    val randomIndex by remember { mutableIntStateOf(Random.nextInt(0, AvatarCode.values().size)) }
    val randomAvatar = AvatarCode.values()[randomIndex]

    // 아바타 이름
    var name = randomAvatar.avatarDefaultName

    Column(
        modifier = Modifier.height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box( // 갓 부화한 아바타와 깨진 알
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = randomAvatar.imageId),
                contentDescription = "아바타",
                modifier = Modifier
                    .size(130.dp)
                    .padding(bottom = 30.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.egg_cracked),
                contentDescription = "깨진 알",
                modifier = Modifier.size(110.dp, 90.dp)
            )
        }
        Box() {// NameAvatar 캐릭터 기본 이름 전달을 위한 composable
            onNameChange(name)
        }
        CommonTextField( // 캐릭터 이름 입력
            defaultValue = name,
            onValueChange = {
                name = it
                onNameChange(it)
            },
            keyboardType = KeyboardType.Text,
            topPadding = 20,
        )
    }
}

@Composable
fun NameAvatar(
    navController: NavController
) {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "아바타 이름을 입력해 주세요",
            style = gmarketsansTypo.headlineLarge,
            color = Gray900
        )
        Text(
            text = "*4자 이내로 입력해 주세요",
            style = gmarketsansTypo.titleSmall,
            color = Gray900
        )
        NewAvatar() {
            name = it
        }
        CommonButton(
            text = "입력 완료",
            enabled = (name.length < 5) && (name.isNotEmpty()),
            onClick = {
                navController.navigate("sleepGoal")
            },
        )
    }
}