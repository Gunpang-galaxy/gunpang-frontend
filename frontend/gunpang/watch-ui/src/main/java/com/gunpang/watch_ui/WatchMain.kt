package com.gunpang.watch_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.gunpang.common.code.InitCode
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.exercise.ExerciseViewModel
import com.gunpang.domain.watch.WatchLandingViewModel
import com.gunpang.domain.watch.WatchAvatarViewModel
import com.gunpang.domain.watch.WatchRecordViewModel
import com.gunpang.watch_ui.record.TodayRecordScreen
import com.gunpang.watch_ui.exercise.afterExercise.AfterExercise
import com.gunpang.watch_ui.exercise.onExercise.OnExercise
import com.gunpang.watch_ui.food.selectFood.SelectFoodScreen
import com.gunpang.watch_ui.landing.LandingScreen

@Composable
fun WatchMain(watchLandingViewModel: WatchLandingViewModel) {

    if(watchLandingViewModel.initCode == InitCode.FINISH){
        // [2] 성공
        WatchMainNavigation()
        return
    }
    // [1] 랜딩 중
    LandingScreen(watchLandingViewModel)
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WatchMainNavigation() {
    val navController = rememberSwipeDismissableNavController()
    val pageCount by remember { mutableIntStateOf(3) }
    val mainPagerState = rememberPagerState(initialPage = 1,pageCount={pageCount})
    val coroutineScope = rememberCoroutineScope()

    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    val watchAvatarViewModel = viewModel<WatchAvatarViewModel>(viewModelStoreOwner)
    val watchRecordViewModel = viewModel<WatchRecordViewModel>(viewModelStoreOwner)
     val exerciseViewModel = hiltViewModel<ExerciseViewModel>()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = WatchNavItem.Main.route
    ) {
        // Main : ExerciseScreen AvatarScreen FoodScreen
        composable(route = WatchNavItem.Main.route) {
            MainScreen(
                mainPagerState,
                coroutineScope,
                navController,
                watchAvatarViewModel,
                exerciseViewModel
            )
        }
        /** 히스토리 **/
        // TodayHistory
        composable(route=WatchNavItem.TodayHistory.route){
            TodayRecordScreen(
                mainPagerState,
                coroutineScope,
                navController,
                watchRecordViewModel
            )
        }
        /** 운동 **/
        // OnExercise : NowExerciseStatus ExerciseMenu
        composable(route=WatchNavItem.OnExercise.route){
            OnExercise(
                mainPagerState,
                coroutineScope,
                navController,
                exerciseViewModel
            )
        }
        // AfterExercise
        composable(route=WatchNavItem.AfterExercise.route){
            AfterExercise(
                mainPagerState,
                coroutineScope,
                navController,
                exerciseViewModel
            )
        }

        /** 음식 **/
        // SelectFood
        composable(route=WatchNavItem.SelectFood.route){
            SelectFoodScreen(
                mainPagerState,
                coroutineScope,
                navController,
            )
        }
    }
}
