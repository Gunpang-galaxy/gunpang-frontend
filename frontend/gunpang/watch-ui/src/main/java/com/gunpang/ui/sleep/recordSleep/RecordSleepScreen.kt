package com.gunpang.ui.sleep.recordSleep

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import com.gunpang.common.R
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.data.model.request.SleepDataReqDto
import com.gunpang.domain.watch.WatchSleepViewModel
import com.gunpang.ui.common.GunpangScreenWrapper
import com.gunpang.ui.common.WatchDivider
import com.gunpang.ui.theme.galmuri
import com.gunpang.ui.common.CommonTextField
import com.gunpang.ui.common.WatchButton
import com.gunpang.ui.theme.galmuriTyop
import kotlinx.coroutines.CoroutineScope

@Composable
fun SleepTime(defaultHour: String, defaultMinute: String, onHourChange: (Int) -> Unit, onMinuteChange: (Int) -> Unit) {
    var hour by remember { mutableStateOf(defaultHour) }
    var minute by remember { mutableStateOf(defaultMinute) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CommonTextField(
            defaultValue = hour,
            onValueChange = {
                hour = it
                onHourChange(it.toInt())
            },
            width = 80,
            rightPadding = 10,
        )
        androidx.compose.material3.Text(
            text = ":",
            style = galmuriTyop.title1,
            color =  Color.White
        )
        CommonTextField(
            defaultValue = minute,
            onValueChange = {
                minute = it
                onMinuteChange(it.toInt())
            },
            width = 80,
            leftPadding = 10,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordSleepScreen(
    mainPagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController
) {
    //스크롤 !!
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    val watchSleepViewModel = viewModel<WatchSleepViewModel>(viewModelStoreOwner)
    var startTime by remember { mutableStateOf("") }
    var startMinute by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    var endMinute by remember { mutableStateOf("") }

    GunpangScreenWrapper {
        val listState = rememberScalingLazyListState()
        Image(
            painter = painterResource(id = R.drawable.sleep),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "오늘 잔 시간",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = galmuri
        )
        WatchDivider()
        //수면 기록하기
        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item{
            SleepTime( // 취침 시간 입력
                defaultHour = startTime,
                defaultMinute = startMinute,
                onHourChange = { startTime = it.toString() }
            ) { startMinute = it.toString() }
            }
            item {
                androidx.compose.material3.Text(
                    text = "~",
                    style = galmuriTyop.title1,
                    color = Color.White,
                )
            }
            item {
                SleepTime( // 기상 시간 입력
                    defaultHour = endTime,
                    defaultMinute = endMinute,
                    onHourChange = { endTime = it.toString() }
                ) { endMinute = it.toString() }
            }
            item{Spacer( // 시간 입력 창과 버튼 사이 간격
                modifier = Modifier.size(20.dp)
            )}
            item {
                WatchButton(
                    text = "기록",
                    onClick = {
                        timeAvailable(startTime, startMinute) && timeAvailable(
                            endTime,
                            endMinute
                        )
                        // TODO: 입력한 시간을 viewmodel에 전송
                        watchSleepViewModel.recordSleep(
                            SleepDataReqDto(
                                sleepStartHour = startTime.toInt(),
                                sleepStartMinute = startMinute.toInt(),
                                sleepEndHour = endTime.toInt(),
                                sleepEndMinute = endMinute.toInt()
                            )
                        )
                        navController.navigate(WatchNavItem.Main.route)
                    }
                )
            }
        }
    }
}

// 입력 받은 시간이 유효한지 확인
fun timeAvailable(hour: String, minute: String ): Boolean {
    try {
        return hour.toInt() in 0..23 && minute.toInt() in 0..59
    } catch (e: Exception) {
        return false
    }
}



