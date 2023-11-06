package com.gunpang.ui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gunpang.common.code.InitCode
import com.gunpang.common.navigation.AppNavItem
import com.gunpang.domain.app.AppViewModel
import com.gunpang.domain.app.avatar.AvatarViewModel
import com.gunpang.domain.app.landing.LoginViewModel
import com.gunpang.domain.app.user.UserViewModel
import com.gunpang.ui.app.screen.avatar.AvatarEgg
import com.gunpang.ui.app.screen.avatar.NameAvatar
import com.gunpang.ui.app.screen.calender.CalenderScreen
import com.gunpang.ui.app.screen.goal.ExerciseGoal
import com.gunpang.ui.app.screen.goal.SleepGoal
import com.gunpang.ui.app.screen.landing.Introduction
import com.gunpang.ui.app.screen.landing.LinkSamsungHealth
import com.gunpang.ui.app.screen.landing.Login
import com.gunpang.ui.app.screen.landing.PersonalInfo
import com.gunpang.ui.app.screen.main.AvatarFinishedScreen
import com.gunpang.ui.app.screen.main.MainScreen
import com.gunpang.ui.app.screen.mypage.MyPageScreen
import com.gunpang.ui.theme.GunpangTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMain(
    loginViewModel: LoginViewModel,
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    val appViewModel = viewModel<AppViewModel>(viewModelStoreOwner)

    loginViewModel.login() // 초기 상태 확인
    GunpangTheme {
        Scaffold { fullScreen ->
            Box(modifier = Modifier.padding(fullScreen)) {
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
                when (loginViewModel.initCode) {
                    InitCode.NOT_LOGIN -> { // 로그인 되지 않은 상태
                        Login(loginViewModel = loginViewModel)
                    }
                    InitCode.REGISTER -> { // 회원가입 필요
                        AppNavGraph(startDestination = AppNavItem.Introduction.routeName)
                    }
                    InitCode.NOT_CONFIG -> { // 신체 정보 입력 X
                        AppNavGraph(startDestination = AppNavItem.PersonalInfo.routeName)
                    }
                    InitCode.FINISH -> {
                        AppNavGraph()
                    }
                    else -> {
                        // TODO : 예외 페이지로 이동
                        Login(loginViewModel = loginViewModel)
                    }
                }
            }
        }
    }
}


@Composable
fun AppNavGraph(
    startDestination: String = AppNavItem.MainScreen.routeName
) {
    val navController = rememberNavController()
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    val appViewModel = viewModel<AppViewModel>(viewModelStoreOwner)
    val avatarViewModel = viewModel<AvatarViewModel>(viewModelStoreOwner)
    val userViewModel = viewModel<UserViewModel>(viewModelStoreOwner)

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
            MainScreen(navController, appViewModel, avatarViewModel)
        }
        composable(AppNavItem.MyPageScreen.routeName) {
            MyPageScreen(navController, userViewModel)
        }
        composable(AppNavItem.CalenderScreen.routeName) {
            CalenderScreen(navController, appViewModel)
        }
        composable(AppNavItem.BodyCompositionScreen.routeName) {
            CalenderScreen(navController, appViewModel)
        }
        composable(AppNavItem.AvatarFinishScreen.routeName) {
            AvatarFinishedScreen(navController, avatarViewModel)
        }
    }
}
