package com.gunpang.ui.sleep

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.wear.compose.material.Text
import com.gunpang.common.R.drawable.*
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.WatchRecordViewModel
import com.gunpang.ui.common.WatchButton
import com.gunpang.ui.common.WatchChip
import com.gunpang.ui.theme.galmuri
import kotlinx.coroutines.CoroutineScope

//@Preview(name = "수면 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SleepScreen(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    watchRecordViewModel: WatchRecordViewModel
) {
    LaunchedEffect(true){
        watchRecordViewModel.init()
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = sleep),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "..zZ",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = galmuri
        )

        Spacer(modifier = Modifier.height(6.dp))

        if(watchRecordViewModel.sleepTime == "00시간 00분") {
            WatchButton(text = "언제 잤어?") {
                navController.navigate(WatchNavItem.RecordSleep.route)
            }
        }else{
            WatchChip(label = "수면 시간"){
                Text(text = watchRecordViewModel.sleepTime, fontFamily = galmuri, fontSize = 21.sp)
            }
        }
    }

}