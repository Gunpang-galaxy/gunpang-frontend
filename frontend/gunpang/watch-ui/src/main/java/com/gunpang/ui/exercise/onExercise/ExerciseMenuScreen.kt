package com.gunpang.ui.exercise.onExercise

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.data.service.SummaryScreenState
import com.gunpang.domain.watch.exercise.ExerciseViewModel
import com.gunpang.ui.common.WatchButton

@Composable
fun ExerciseMenuScreen(navController: NavHostController,
                       exerciseViewModel: ExerciseViewModel,
                       onSummary: (SummaryScreenState) -> Unit) {
    val uiState by exerciseViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (uiState.isEnded) {
            SideEffect {
                onSummary(uiState.toSummary())
            }
        }
        if (uiState.isPaused) {
            WatchButton(text = "재개하기") {
                // TODO: 운동 재개
                exerciseViewModel.resumeExercise()
                Log.d("EXERCISE_BUTTON","재개하기")
            }
        } else {
            WatchButton(text = "일시정지") {
                // TODO: 일시정지
                exerciseViewModel.pauseExercise()
                Log.d("EXERCISE_BUTTON","일시정지")
            }
        }

        WatchButton(text = "그만하기") {
            exerciseViewModel.endExercise()
        }
    }
}