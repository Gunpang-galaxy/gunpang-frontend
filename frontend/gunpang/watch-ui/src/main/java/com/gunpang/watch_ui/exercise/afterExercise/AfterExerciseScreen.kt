package com.gunpang.watch_ui.exercise.afterExercise

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Text
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.exercise.ExerciseViewModel
import com.gunpang.watch_ui.common.GunpangScreenWrapper
import com.gunpang.watch_ui.common.WatchButton
import com.gunpang.watch_ui.exercise.formatElapsedTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import java.time.Duration

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AfterExercise(
    mainPagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    exerciseViewModel: ExerciseViewModel
) {
    val secondsLeft = remember { mutableStateOf(5) }
    GunpangScreenWrapper {
        Text(""+formatElapsedTime(exerciseViewModel.elapsedTime, includeSeconds = true), fontSize = 25.sp)
        Text("${secondsLeft.value}초 후 화면 이동", fontSize = 12.sp)
        WatchButton(text = "운동 완료", onClick = { navController.navigate(WatchNavItem.Main.route) })

        LaunchedEffect(Unit) {
            while (secondsLeft.value > 0) {
                delay(1000)  // 1초 대기
                secondsLeft.value--
            }
            navController.navigate(WatchNavItem.Main.route)
        }
    }
}
