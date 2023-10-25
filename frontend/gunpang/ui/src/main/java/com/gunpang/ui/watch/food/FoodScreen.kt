package com.gunpang.ui.app.watch.food

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.gunpang.common.R.drawable.*
import com.gunpang.ui.theme.galmuri
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.ui.app.watch.common.WatchButton

@Preview(name = "음식 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun FoodScreen(){
    GunpangScreenWrapper {

        Image(
            painter = painterResource(id = baccus), // 여기서 'your_icon'을 실제 리소스 이름으로 바꾸어야 합니다.
            contentDescription = null,
            modifier = Modifier.size(36.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "나 배고파 ㅠ",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = galmuri
        )

        Spacer(modifier = Modifier.height(6.dp))

        WatchButton("밥먹이기") {
        }


    }
}