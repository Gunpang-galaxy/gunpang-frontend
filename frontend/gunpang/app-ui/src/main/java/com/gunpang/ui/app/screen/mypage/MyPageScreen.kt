package com.gunpang.ui.app.screen.mypage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.gunpang.domain.app.AppViewModel
import com.gunpang.domain.app.user.UserViewModel
import com.gunpang.ui.app.common.TopBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyPageScreen(
    navController: NavController,
    appViewModel: AppViewModel,
    userViewModel: UserViewModel
){
    LaunchedEffect(key1 = true){
        userViewModel.init()
    }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = "내 정보",
                hasUndo = true,
                )
        },
        containerColor = Color.White
    ) {
        it ->
        Surface(modifier = Modifier.padding(it)) {
            Box(){
                // TODO : 내 정보
                //이메일
                // 성별
                // 출생
                // 키
                // 몸무게
                // TODO : 로그아웃 버튼
            }
        }
    }
}

