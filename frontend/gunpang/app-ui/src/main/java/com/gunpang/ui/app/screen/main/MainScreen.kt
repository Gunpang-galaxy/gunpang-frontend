package com.gunpang.ui.app.screen.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gunpang.common.R
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.navigation.AppNavItem
import com.gunpang.domain.app.AppViewModel
import com.gunpang.domain.app.avatar.AvatarViewModel
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.app.common.TopBar
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.GunpangTheme
import com.gunpang.ui.theme.Navy50
import com.gunpang.ui.theme.gmarketsans
import com.gunpang.ui.theme.gmarketsansBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    appViewModel: AppViewModel,
    avatarViewModel: AvatarViewModel
){
    LaunchedEffect(key1 = true){
        // ui 접근 시 한번만 실행
        avatarViewModel.init()
        Log.d("[main avatar]", avatarViewModel.appAvatar.toString())
        // 현재 아바타가 살아있는 상태가 아닐 경우
        if(avatarViewModel.appAvatar.status != AvatarStatusCode.ALIVE){
            navController.navigate(AppNavItem.AvatarFinishScreen.routeName)
        }
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
                MainContent(avatarViewModel = avatarViewModel)
            }
    }
}