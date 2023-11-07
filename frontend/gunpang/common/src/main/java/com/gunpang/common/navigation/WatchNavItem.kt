package com.gunpang.common.navigation

import java.time.Duration

sealed class WatchNavItem(val route: String) {
    object Main: WatchNavItem("main")
    /** 히스토리 **/
    object TodayHistory : WatchNavItem("today-history")

    /** 운동 **/
    object OnExercise: WatchNavItem("on-exercise")
    object AfterExercise: WatchNavItem("after-exercise"){
        fun createRoute(elapsedTime: Duration, calories: Double, distance: Double): String {
            return "after-exercise?elapsedTime=${elapsedTime.toMillis()}&calories=$calories&distance=$distance"
        }
    }

    /** 음식 **/
    object SelectFood: WatchNavItem("select-food")

}