package com.gunpang.ui.app.screen.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gunpang.common.R
import com.gunpang.domain.app.landing.LandingViewModel
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.galmuriTyop

@Composable
fun Login(
    landingViewModel: LandingViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "건팡",
            style = galmuriTyop.displayLarge,
            color = Gray900
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "건팡 logo",
            modifier = Modifier
                .size(180.dp, 200.dp)
        )
        Text(
            text = "\"어디로든 갈래 무한한 galaxy\"",
            style = galmuriTyop.bodySmall,
            color = Gray900
        )
        Spacer(
            modifier = Modifier.size(70.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.google_login),
            contentDescription = "구글 로그인 버튼",
            modifier = Modifier
                .height(50.dp)
                .clickable { landingViewModel.login() }
        )
    }
}