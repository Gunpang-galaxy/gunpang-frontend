package com.gunpang.watch_ui.exercise.onExercise

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.exercise.ExerciseViewModel
import com.gunpang.watch_ui.common.WatchButton

@Composable
fun ExerciseMenuScreen(navController: NavHostController, exerciseViewModel: ExerciseViewModel) {
    val isPaused = remember { mutableStateOf(false) }
    val uiState by exerciseViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isPaused.value) {
            WatchButton(text = "재개하기") {
                // TODO: 운동 재개
                exerciseViewModel.resumeExercise()
                isPaused.value = false
            }
        } else {
            WatchButton(text = "일시정지") {
                // TODO: 일시정지
                exerciseViewModel.pauseExercise()
                isPaused.value = true
            }
        }
        WatchButton(text = "그만하기") {
            exerciseViewModel.endExercise()
            exerciseViewModel.elapsedTime = uiState.toSummary().elapsedTime
            navController.navigate(WatchNavItem.AfterExercise.route)
        }
    }
}