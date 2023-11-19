package com.gunpang.ui

import android.util.Log
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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.gunpang.common.code.InitCode
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.exercise.ExerciseViewModel
import com.gunpang.domain.watch.WatchLandingViewModel
import com.gunpang.domain.watch.WatchAvatarViewModel
import com.gunpang.domain.watch.WatchRecordViewModel
import com.gunpang.ui.record.TodayRecordScreen
import com.gunpang.ui.exercise.afterExercise.AfterExercise
import com.gunpang.ui.exercise.onExercise.OnExercise
import com.gunpang.ui.food.selectFood.SelectFoodScreen
import com.gunpang.ui.landing.LandingScreen
import com.gunpang.ui.sleep.recordSleep.RecordSleepScreen
import java.time.Duration

@Composable
fun WatchMain(watchLandingViewModel: WatchLandingViewModel) {

    if(watchLandingViewModel.initCode == InitCode.FINISH){
        // [2] 성공
        WatchMainNavigation()
        return
    }
    // [1] 랜딩 중
    LandingScreen(watchLandingViewModel)
//    WatchMainNavigation()

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WatchMainNavigation() {
    val navController = rememberSwipeDismissableNavController()
    val pageCount by remember { mutableIntStateOf(4) }
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
                exerciseViewModel,
                watchRecordViewModel
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
        composable(
            route =  WatchNavItem.AfterExercise.route + "?elapsedTime={elapsedTime}&calories={calories}&distance={distance}",
            arguments = listOf(
                navArgument("elapsedTime") { type = NavType.LongType },
                navArgument("calories") { type = NavType.FloatType  },
                navArgument("distance") { type = NavType.FloatType  }
            )
        ) { backStackEntry ->
            val durationMillis = backStackEntry.arguments?.getLong("elapsedTime") ?: 0L
            val elapsedTime = Duration.ofMillis(durationMillis)
            val calories = backStackEntry.arguments?.getFloat("calories")?:0f
            val distance = backStackEntry.arguments?.getFloat("distance")?:0f
            Log.d("NAV_ROUTER",""+elapsedTime+" "+calories+" "+distance)
            AfterExercise(
                mainPagerState,
                coroutineScope,
                navController,
                elapsedTime = elapsedTime,
                calories = calories,
                distance = distance
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

        /** 수면 **/
        // RecordSleep
        composable(route=WatchNavItem.RecordSleep.route){
            RecordSleepScreen(
                mainPagerState,
                coroutineScope,
                navController,
            )
        }
    }
}
