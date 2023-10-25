package com.gunpang.ui.watch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.common.R
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.GunpangTheme
import com.gunpang.ui.theme.Navy200
@Preview(name="워치 메인 화면",device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun WatchMain (){
        GunpangTheme {
            /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
             * version of LazyColumn for wear devices with some added features. For more information,
             * see d.android.com/wear/compose.
             */
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray900),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.baccus), // 여기서 'your_icon'을 실제 리소스 이름으로 바꾸어야 합니다.
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "나 배고파 ㅠ",
                        color = Color.White,
                        fontSize = 20.sp,
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Button(
                        onClick = { /* 버튼 클릭 이벤트 처리 */ },
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .background(color = Navy200),
                    ) {
                        Text(text = "밥먹이기", color = Gray900)
                    }
                }

            }
        }
}