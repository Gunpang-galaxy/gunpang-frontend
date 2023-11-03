package com.gunpang.watch_ui.exercise.onExercise

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.gunpang.domain.watch.ExerciseViewModel
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.ui.app.watch.exercise.ExerciseMenuScreen
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
    val pagerState = rememberPagerState(initialPage = 0)

    GunpangScreenWrapper {
        Column(
            modifier = Modifier.fillMaxSize(),
            ) {
            VerticalPager(
                modifier = Modifier.weight(0.9f),
                state = pagerState,
                pageCount = pageCount
            ) { page ->
                when (page) {
                    0 -> CurrentStatusScreen(exerciseViewModel)
                    1 -> ExerciseMenuScreen(navController,exerciseViewModel)
                }
            }

        }
    }
}