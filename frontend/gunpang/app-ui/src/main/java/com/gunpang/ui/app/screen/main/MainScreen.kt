package com.gunpang.ui.app.screen.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.navigation.AppNavItem
import com.gunpang.data.manager.AppHealthConnectManager
import com.gunpang.data.manager.HealthConnectAvailability
import com.gunpang.data.repository.DataApplicationRepository
import com.gunpang.domain.app.AppViewModel
import com.gunpang.domain.app.avatar.AvatarViewModel
import com.gunpang.domain.app.healthconnect.AppHealthViewModel
import com.gunpang.domain.app.landing.NotificationViewModel
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.app.common.TopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    avatarViewModel: AvatarViewModel,
    notificationViewModel: NotificationViewModel = viewModel(),
    healthConnectAvailability: HealthConnectAvailability,
    healthConnectManager: AppHealthConnectManager,
    permissions: Set<String>,
    permissionGranted: Boolean,
    onPermissionsResult: () -> Unit = {},
    onPermissionsLaunch: (Set<String>) -> Unit = {},
    uiState: AppHealthViewModel.UiState
){
    val errorId = rememberSaveable {
        mutableStateOf(
            java.util.UUID.randomUUID()
        )
    }


    LaunchedEffect(uiState){
        if(uiState is AppHealthViewModel.UiState.Uninitialized){
            Log.d("[MainScreen - uiState]", uiState.toString().toString())
            onPermissionsResult()
        }
        if(uiState is AppHealthViewModel.UiState.Error){
            errorId.value = uiState.uuid
        }
    }

    // ui 접근 시 한번만 실행
    LaunchedEffect(key1 = true){
        // 헬스 커넥트 사용 가능 여부
        when(healthConnectAvailability) {
            HealthConnectAvailability.INSTALLED -> Log.d("[health connect availability]", "installed")
            HealthConnectAvailability.NOT_INSTALLED -> Log.d("[health connect availability]", "not installed")
            HealthConnectAvailability.NOT_SUPPORTED -> Log.d("[health connect availability]", "not supported")
        }

        Log.d("[permissionGranted]", permissionGranted.toString())
        // 헬스 커넥트 권한 부여
        if (!permissionGranted){
            Log.d("[permission]", permissions.toString())
            onPermissionsLaunch(permissions)
        }

        avatarViewModel.init()
        delay(200) // 0.2초 딜레이(아바타 존재여부 확인을 위한 딜레이)

        Log.d("[main avatar]", "현재 아바타 존재 여부: ${avatarViewModel.currentAvatarExist.toString()}")
        Log.d("[main avatar]", avatarViewModel.appAvatar.toString())

        // 현재 아바타가 없는 경우
        if (!avatarViewModel.currentAvatarExist) {
            navController.navigate(AppNavItem.AvatarNotCreatedException.routeName)
        }

        // 현재 아바타가 살아있는 상태가 아닐 경우
        if(avatarViewModel.appAvatar.status != AvatarStatusCode.ALIVE){
            navController.navigate(AppNavItem.AvatarFinishScreen.routeName)
        }
    }

    // fcm 토큰 등록
    LaunchedEffect(key1 = DataApplicationRepository().getValue("fcmToken")) {
        notificationViewModel.registerFCMTokens()
    }

    Scaffold(
        topBar={
            TopBar(
                navController = navController,
                title= ""
            ) },
        containerColor= Color.White,
        bottomBar = { BottomNavBar(navController = navController) },
        ) {
            paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(paddingValues),
                color = Color.White
            ){
                MainContent(
                    avatarViewModel = avatarViewModel,
                    healthConnectManager = healthConnectManager
                )
            }
    }
}