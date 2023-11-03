package com.gunpang.common.navigation

sealed class AppNavItem(
    val routeName: String,
    val description: String
) {
    object Login: AppNavItem("login", "로그인 화면")
    object Introduction: AppNavItem("introduction", "앱 소개 화면")
    object PersonalInfo: AppNavItem("personalInfo", "개인 신체 정보 화면")
    object LinkSamsungHealth: AppNavItem("linkSamsungHealth", "삼성 헬스 링크 화면")
    object AvatarEgg: AppNavItem("avatarEgg", "아바타 랜덤 뽑기 화면")
    object NameAvatar: AppNavItem("nameAvatar", "아바타 이름 짓기 화면")
    object SleepGoal: AppNavItem("sleepGoal", "수면 목표 화면")
    object ExerciseGoal: AppNavItem("exerciseGoal", "운동 목표 화면")
    object MainScreen: AppNavItem("mainScreen", "메인 화면")
    object MyPageScreen: AppNavItem("myPageScreen", "내정보 화면")
    object CalenderScreen: AppNavItem("calenderScreen", "달력 내 기록 화면")
    object BodyCompositionScreen: AppNavItem("bodyCompositionScreen", "채성분 분석 화면")
    object AvatarFinishScreen: AppNavItem("AvatarFinishScreen", "아바타 성장 종료 화면")

}