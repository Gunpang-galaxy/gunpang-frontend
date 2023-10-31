package com.gunpang.watch_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.WatchLandingViewModel
import com.gunpang.domain.watch.AvatarViewModel
import com.gunpang.domain.watch.TodayHistoryViewModel
import com.gunpang.ui.app.watch.MainScreen
import com.gunpang.ui.app.watch.history.HistoryScreen
import com.gunpang.watch_ui.exercise.afterExercise.AfterExercise
import com.gunpang.watch_ui.exercise.onExercise.OnExercise
import com.gunpang.watch_ui.food.selectFood.SelectFoodScreen

@Composable
fun WatchMain(watchLandingViewModel: WatchLandingViewModel) {
    //val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    //val watchViewModel = viewModel<WatchViewModel>(viewModelStoreOwner)

    /*
    if(watchLandingViewModel.initCode == InitCode.FINISH){
        // [2] 성공
        WatchMainNavigation()
        return
    }
    // [1] 랜딩 중
    LandingScreen(watchLandingViewModel)
    */
    WatchMainNavigation()
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WatchMainNavigation(){
    val navController = rememberSwipeDismissableNavController()
    val mainPagerState = rememberPagerState(1)
    val coroutineScope = rememberCoroutineScope()

    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    val avatarViewModel = viewModel<AvatarViewModel>(viewModelStoreOwner)
    val todayHistoryViewModel = viewModel<TodayHistoryViewModel>(viewModelStoreOwner)
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
                avatarViewModel
            )
        }
        /** 히스토리 **/
        // TodayHistory
        composable(route=WatchNavItem.TodayHistory.route){
            HistoryScreen(
                mainPagerState,
                coroutineScope,
                navController,
                todayHistoryViewModel
            )
        }
        /** 운동 **/
        // OnExercise : NowExerciseStatus ExerciseMenu
        composable(route=WatchNavItem.OnExercise.route){
            OnExercise(
                mainPagerState,
                coroutineScope,
                navController,
            )
        }
        // AfterExercise
        composable(route=WatchNavItem.AfterExercise.route){
            AfterExercise(
                mainPagerState,
                coroutineScope,
                navController,
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
