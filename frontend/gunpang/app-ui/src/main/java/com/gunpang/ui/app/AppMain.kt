package com.gunpang.ui.app

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gunpang.common.code.InitCode
import com.gunpang.common.navigation.AppNavItem
import com.gunpang.domain.app.AppViewModel
import com.gunpang.domain.app.avatar.AvatarViewModel
import com.gunpang.domain.app.landing.GoalViewModel
import com.gunpang.domain.app.landing.LandingViewModel
import com.gunpang.domain.app.calendar.CalendarRecordViewModel
import com.gunpang.domain.app.user.UserViewModel
import com.gunpang.ui.app.screen.avatar.AvatarEgg
import com.gunpang.ui.app.screen.avatar.NameAvatar
import com.gunpang.ui.app.screen.bodyComposition.BodyCompositionScreen
import com.gunpang.ui.app.screen.calender.CalenderScreen
import com.gunpang.ui.app.screen.goal.ExerciseGoal
import com.gunpang.ui.app.screen.goal.SleepGoal
import com.gunpang.ui.app.screen.landing.AvatarNotCreatedException
import com.gunpang.ui.app.screen.landing.GoalNotCreatedException
import com.gunpang.ui.app.screen.landing.Introduction
import com.gunpang.ui.app.screen.landing.LinkSamsungHealth
import com.gunpang.ui.app.screen.landing.Login
import com.gunpang.ui.app.screen.landing.LoginFailException
import com.gunpang.ui.app.screen.landing.LookForConnection
import com.gunpang.ui.app.screen.landing.PersonalInfo
import com.gunpang.ui.app.screen.landing.WatchAppNotInstalledException
import com.gunpang.ui.app.screen.landing.WatchNotConnectedException
import com.gunpang.ui.app.screen.main.AvatarFinishedScreen
import com.gunpang.ui.app.screen.main.MainScreen
import com.gunpang.ui.app.screen.mypage.MyPageScreen
import com.gunpang.ui.theme.GunpangTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMain(
    landingViewModel: LandingViewModel
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    val appViewModel = viewModel<AppViewModel>(viewModelStoreOwner)

    // 워치 연동 여부, 로그인 여부 확인
    LaunchedEffect(key1 = true) {
        delay(300) // Mainactivity onResume실행을 위한 대기 시간
        landingViewModel.login()
    }
    Log.d("landing", "initCode2: ${landingViewModel.initCode}")

    GunpangTheme {
        Scaffold { fullScreen ->
            Box(
                modifier = Modifier
                    .padding(fullScreen)
                    .background(Color.White)
            ) {
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
                when (landingViewModel.initCode) {
                    InitCode.NOT_CONFIG -> { // 초기 설정 안됨
                        AppNavGraph(
                            startDestination = AppNavItem.LookForConnection.routeName,
                            landingViewModel = landingViewModel
                        )
                    }

                    InitCode.NOT_FOUND -> { // 기기 없음
                        AppNavGraph(
                            startDestination = AppNavItem.WatchNotConnectedException.routeName,
                            landingViewModel = landingViewModel
                        )
                    }

                    InitCode.NOT_INSTALL -> { // 워치에 앱 설치 안됨
                        AppNavGraph(
                            startDestination = AppNavItem.WatchAppNotInstalledException.routeName,
                            landingViewModel = landingViewModel
                        )
                    }

                    InitCode.NOT_LOGIN -> { // 로그인 되지 않은 상태
                        Login(
                            navController = rememberNavController(),
                            landingViewModel = landingViewModel
                        )
                    }

                    InitCode.REGISTER -> { // 회원가입 필요
                        AppNavGraph(
                            startDestination = AppNavItem.Introduction.routeName,
                            landingViewModel = landingViewModel
                        )
                    }

                    InitCode.FINISH -> {
                        AppNavGraph(
                            landingViewModel = landingViewModel
                        )
                    }

                    else -> {
                        // 인터넷 연결 X
                        // wearOS 앱 X
                    }
                }
            }
        }
    }
}


@Composable
fun AppNavGraph(
    startDestination: String = AppNavItem.MainScreen.routeName,
    landingViewModel: LandingViewModel
) {
    val navController = rememberNavController()
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    val appViewModel = viewModel<AppViewModel>(viewModelStoreOwner)
    val avatarViewModel = viewModel<AvatarViewModel>(viewModelStoreOwner)
    val userViewModel = viewModel<UserViewModel>(viewModelStoreOwner)
    val calendarRecordViewModel = viewModel<CalendarRecordViewModel>(viewModelStoreOwner)

    val goalViewModel = viewModel<GoalViewModel>(viewModelStoreOwner)
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppNavItem.Login.routeName) {
            Login(navController, landingViewModel)
        }
        composable(AppNavItem.AppMain.routeName) {
            AppMain(landingViewModel)
        }
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
            SleepGoal(navController, goalViewModel)
        }
        composable(AppNavItem.ExerciseGoal.routeName) {
            ExerciseGoal(navController, goalViewModel)
        }
        composable(AppNavItem.MainScreen.routeName) {
            MainScreen(navController, appViewModel, avatarViewModel)
        }
        composable(AppNavItem.MyPageScreen.routeName) {
            MyPageScreen(navController, userViewModel, landingViewModel)
        }
        composable(AppNavItem.CalenderScreen.routeName) {
            CalenderScreen(navController,calendarRecordViewModel)
        }
        composable(AppNavItem.BodyCompositionScreen.routeName) {
            BodyCompositionScreen(navController)
        }
        composable(AppNavItem.AvatarFinishScreen.routeName) {
            AvatarFinishedScreen(navController, avatarViewModel)
        }
        composable(AppNavItem.LoginFailException.routeName) {
            LoginFailException(navController)
        }
        composable(AppNavItem.WatchNotConnectedException.routeName) {
            WatchNotConnectedException(navController, landingViewModel)
        }
        composable(AppNavItem.WatchAppNotInstalledException.routeName) {
            WatchAppNotInstalledException(navController, landingViewModel)
        }
        composable(AppNavItem.AvatarNotCreatedException.routeName) {
            AvatarNotCreatedException(navController)
        }
        composable(AppNavItem.GoalNotCreatedException.routeName) {
            GoalNotCreatedException(navController)
        }
        composable(AppNavItem.LookForConnection.routeName) {
            LookForConnection(navController)
        }
    }
}
