package com.gunpang.ui.app.watch.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import com.gunpang.common.R
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.TodayHistoryViewModel
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.watch_ui.common.WatchButton
import com.gunpang.watch_ui.common.WatchChip
import com.gunpang.watch_ui.common.WatchDivider
import com.gunpang.watch_ui.theme.galmuri
import kotlinx.coroutines.CoroutineScope

//@Preview(name = "히스토리 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryScreen(
    mainPagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    todayHistoryViewModel: TodayHistoryViewModel
) {
    LaunchedEffect(true){

    }
    GunpangScreenWrapper {
        val listState = rememberScalingLazyListState()
        Image(
            painter = painterResource(id = R.drawable.report),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "오늘 기록",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = galmuri
        )
        WatchDivider()

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
                        painter = painterResource(id = R.drawable.food_bad_fried), // 이미지 리소스 변경 필요
                        contentDescription = null,
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(4.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.food_healthy_salad), // 이미지 리소스 변경 필요
                        contentDescription = null,
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(4.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.food_normal_rice), // 이미지 리소스 변경 필요
                        contentDescription = null,
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(4.dp)
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
                    navController.navigate(WatchNavItem.Main.route)
                }
            }
        }
    }
}