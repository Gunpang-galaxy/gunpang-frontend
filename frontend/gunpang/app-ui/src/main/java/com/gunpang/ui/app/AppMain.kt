package com.gunpang.ui.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gunpang.common.navigation.AppNavItem
import com.gunpang.domain.app.landing.LoginViewModel
import com.gunpang.ui.app.screen.avatar.AvatarEgg
import com.gunpang.ui.app.screen.avatar.NameAvatar
import com.gunpang.ui.app.screen.goal.ExerciseGoal
import com.gunpang.ui.app.screen.goal.SleepGoal
import com.gunpang.ui.app.screen.landing.Introduction
import com.gunpang.ui.app.screen.landing.LinkSamsungHealth
import com.gunpang.ui.app.screen.landing.PersonalInfo
import com.gunpang.ui.app.screen.main.MainScreen
import com.gunpang.ui.theme.GunpangTheme

@Composable
fun AppMain(
    loginViewModel: LoginViewModel
){
    GunpangTheme {
        /**
         * 앱 로딩 시
         * - 로그인 X: 로그인-> 설명 -> 메인
         *   - 신체 정보 입력 X: 신체 정보 입력 -> 메인
         *   - 신체 정보 입력 O: 메인
         * - 로그인 O: 메인
         *
         * 메인에 필요한 API 시도
         * - 헬스 API 연결 X: 헬스 API 연결 -> 메인
         * - 아바타 X: 아바타 뽑기 -> 목표 입력
         * - 아바타 O:
         *   - 목표 입력 X: 목표 입력 -> 메인
         *   - 목표 입력 O: 메인
         */
        AppNavGraph()
    }
}


@Composable
fun AppNavGraph(
    startDestination: String = AppNavItem.MainScreen.routeName
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppNavItem.Introduction.routeName) {
            Introduction(navController)
        }
        composable(AppNavItem.PersonalInfo.routeName) {
            PersonalInfo(navController)
        }
        composable(AppNavItem.LinkSamsungHealth.routeName) {
            LinkSamsungHealth(navController)
        }
        composable(AppNavItem.AvatarEgg.routeName) {
            AvatarEgg(navController)
        }
        composable(AppNavItem.NameAvatar.routeName) {
            NameAvatar(navController)
        }
        composable(AppNavItem.SleepGoal.routeName) {
            SleepGoal(navController)
        }
        composable(AppNavItem.ExerciseGoal.routeName) {
            ExerciseGoal(navController)
        }
        composable(AppNavItem.MainScreen.routeName) {
            MainScreen()
        }
    }
}
