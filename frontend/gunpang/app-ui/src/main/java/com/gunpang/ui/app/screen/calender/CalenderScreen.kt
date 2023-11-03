package com.gunpang.ui.app.screen.calender

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.gunpang.domain.app.AppViewModel
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.app.common.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalenderScreen(
    navController: NavController,
    appViewModel: AppViewModel
) {
    Scaffold(
        topBar = {
            TopBar(navController = navController, title = "내 기록")
        },
        bottomBar = { BottomNavBar(navController) },
        containerColor = Color.White
    ) {
        it -> Surface(
            modifier = Modifier.padding(it)
        ) {
            // TODO : 달력 화면 구성
        }
    }
}