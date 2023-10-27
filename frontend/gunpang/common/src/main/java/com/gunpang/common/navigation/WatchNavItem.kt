package com.gunpang.common.navigation

sealed class WatchNavItem(val route: String) {
    object Main: WatchNavItem("main")
    /** 히스토리 **/
    object TodayHistory : WatchNavItem("today-history")

    /** 운동 **/
    object OnExercise: WatchNavItem("on-exercise")
    object AfterExercise: WatchNavItem("after-exercise")

    /** 음식 **/
    object SelectFood: WatchNavItem("select-food")

}