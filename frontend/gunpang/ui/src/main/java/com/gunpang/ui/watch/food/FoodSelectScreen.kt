package com.gunpang.ui.watch.food

import android.content.ClipData
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import com.gunpang.common.R
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.ui.app.watch.common.WatchButton
import com.gunpang.ui.theme.galmuri

@Preview(name = "음식 선택 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun FoodSelectScreen() {
    GunpangScreenWrapper {
        val listState = rememberScalingLazyListState()

        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 0),
            state = listState
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baccus), // 여기서 'your_icon'을 실제 리소스 이름으로 바꾸어야 합니다.
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "오늘 먹은 밥",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = galmuri
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }

            }
            item {
                // 건강하게
                WatchButton("기록") {
                }
            }

        }
    }


}

