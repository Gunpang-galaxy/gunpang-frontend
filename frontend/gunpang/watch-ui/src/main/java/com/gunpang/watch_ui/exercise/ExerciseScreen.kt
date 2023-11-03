package com.gunpang.ui.app.watch.exercise

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.exercise.ExerciseViewModel
import com.gunpang.watch_ui.common.WatchButton
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExerciseScreen(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    exerciseViewModel: ExerciseViewModel
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WatchButton(text = "시작하기", onClick = {
            exerciseViewModel.startExercise()
            navController.navigate(WatchNavItem.OnExercise.route)
        })
    }
}