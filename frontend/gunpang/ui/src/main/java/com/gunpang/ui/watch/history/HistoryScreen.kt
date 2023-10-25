package com.gunpang.ui.app.watch.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.ui.app.watch.common.WatchButton
import com.gunpang.ui.app.watch.common.WatchChip
import com.gunpang.ui.theme.galmuri

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
            item { WatchChip(label = "수면 시간"){
                Text(text = "6시간30분", fontFamily = galmuri)
                }
            }
            item { WatchChip(label = "운동 시간"){
                    Text(text = "6시간30분", fontFamily = galmuri)
                }
            }
            item { WatchChip(label = "먹은 밥"){
                }
                /*TODO: 아침, 점심, 저녁*/
            }
            item {
                WatchButton(text = "뒤로가기") {
                }
            }
        }
    }
}