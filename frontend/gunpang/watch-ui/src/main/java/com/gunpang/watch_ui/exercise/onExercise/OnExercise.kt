package com.gunpang.watch_ui.exercise.onExercise

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.exercise.ExerciseViewModel
import com.gunpang.watch_ui.common.GunpangScreenWrapper
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnExercise(
    mainPagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    exerciseViewModel: ExerciseViewModel
) {
    // TODO:  ExerciseMenuScreen, CurrentStatusScreen 으로 이동하기
    val pageCount by remember { mutableIntStateOf(2) }
    val pagerState = rememberPagerState(initialPage = 0,pageCount={pageCount})
    val uiState by exerciseViewModel.uiState.collectAsStateWithLifecycle()
    Log.d("UI_STATE",""+uiState.serviceState+" "+uiState.exerciseState)
    GunpangScreenWrapper {
        Column(
            modifier = Modifier.fillMaxSize(),
            ) {
            VerticalPager(
                modifier = Modifier.weight(0.9f),
                state = pagerState,
            ) { page ->
                when (page) {
                    0 -> CurrentStatusScreen(uiState)
                    1 -> ExerciseMenuScreen(
                        navController = navController,
                        exerciseViewModel = exerciseViewModel,
                        onSummary={
                            navController.navigate(WatchNavItem.AfterExercise
                                .createRoute(it.elapsedTime,it.totalCalories,it.totalDistance))
                        })
                }
            }

        }
    }
}