package com.gunpang.ui.app.watch.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.gunpang.ui.theme.galmuri
import com.gunpang.ui.watch.common.WatchButton
import com.gunpang.ui.watch.common.WatchChip

@Preview(name = "히스토리 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun HistoryScreen() {
    GunpangScreenWrapper {
        val listState = rememberScalingLazyListState()

        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 0),
            state = listState
        ) {
            item { WatchChip(label = "먹은 밥"){
                Row(modifier=Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){
                    Image(
                        painter = painterResource(id = R.drawable.run), // 이미지 리소스 변경 필요
                        contentDescription = null,
                        modifier = Modifier.weight(0.5f).padding(4.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.run), // 이미지 리소스 변경 필요
                        contentDescription = null,
                        modifier = Modifier.weight(0.5f).padding(4.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.run), // 이미지 리소스 변경 필요
                        contentDescription = null,
                        modifier = Modifier.weight(0.5f).padding(4.dp)
                    )
                }
            }
                /*TODO: 아침, 점심, 저녁*/
            }
            item { WatchChip(label = "수면 시간"){
                Text(text = "6시간30분", fontFamily = galmuri, fontSize = 25.sp)
                }
            }
            item { WatchChip(label = "운동 시간"){
                    Text(text = "6시간30분", fontFamily = galmuri, fontSize = 25.sp)
                }
            }

            item {
                WatchButton(text = "뒤로가기") {
                }
            }
        }
    }
}