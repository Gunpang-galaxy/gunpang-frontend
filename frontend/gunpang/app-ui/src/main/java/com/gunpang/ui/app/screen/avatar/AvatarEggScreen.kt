package com.gunpang.ui.app.screen.avatar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gunpang.common.code.AvatarEggCode
import com.gunpang.ui.theme.gmarketsansTypo
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvatarEgg(
    navController: NavController
) {
    // 알 색상 랜덤 선택
    val randomIndex by remember { mutableIntStateOf(Random.nextInt(0, AvatarEggCode.values().size)) }
    val randomAvatarEgg = AvatarEggCode.values()[randomIndex]

    // 5번 터치 후 알 부화
    var touch by remember { mutableIntStateOf(5) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface( // 알 이미지
            onClick = {
                if (touch > 1) touch--
                else navController.navigate("nameAvatar") // 알 부화 후 아바타 이름 짓기 페이지로 이동
            },
        ) {
            Image(
                painter = painterResource(id = randomAvatarEgg.image),
                contentDescription = "아바타 알",
                modifier = Modifier
                    .size(150.dp),
            )
        }
        Spacer( // 알과 텍스트 사이 간격
            modifier = Modifier.size(30.dp)
        )
        Text( // 설명
            text = "알을 부화시켜 보세요",
            style = gmarketsansTypo.headlineLarge,
        )
    }
}