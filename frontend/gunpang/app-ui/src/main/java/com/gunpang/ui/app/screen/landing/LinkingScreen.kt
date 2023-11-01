package com.gunpang.ui.app.screen.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gunpang.common.R
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.theme.gmarketsansTypo

@Composable
fun LinkSamsungHealth(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "건강 데이터를 연동해 주세요",
            style = gmarketsansTypo.headlineLarge,
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = R.drawable.health_connect),
            contentDescription = "health connect 설명",
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .size(200.dp, 100.dp),
        )
        Text(
            text = "헬스 커넥트를 연동하면\n삼성헬스에서 기록을 가지고 올 수 있어요",
            style = gmarketsansTypo.titleSmall,
            textAlign = TextAlign.Center
        )
        Text(
            text = "*안드로이드 버전 9이상부터 연동 가능합니다",
            style = gmarketsansTypo.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer( // 헬스 커넥트 설명과 버튼 사이의 간격
            modifier = Modifier.height(100.dp)
        )
        CommonButton(
            text = "연동하기",
            onClick = {
                navController.navigate("avatarEgg")
            }
        )
    }
}