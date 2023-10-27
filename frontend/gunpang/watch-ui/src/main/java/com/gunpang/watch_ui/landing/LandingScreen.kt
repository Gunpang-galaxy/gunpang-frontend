package com.gunpang.watch_ui.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.watch_ui.theme.Gray300
import com.gunpang.watch_ui.theme.Green500
import com.gunpang.watch_ui.theme.galmuri

@Composable
@Preview(name = "로딩 중 ", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
fun LandingScreen() {
    GunpangScreenWrapper {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "로딩 중...",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = galmuri
            )

            Spacer(modifier = Modifier.height(6.dp))
            /*ProgressBar*/
            LinearProgressIndicator(
                progress = 0.3f,
                color = Green500,
                trackColor = Gray300,
                modifier = Modifier
                    .padding(8.dp)
                    .height(12.dp)
                    .width(107.dp)
                    .clip(RoundedCornerShape(size = 100.dp))
            )
        }
    }
}