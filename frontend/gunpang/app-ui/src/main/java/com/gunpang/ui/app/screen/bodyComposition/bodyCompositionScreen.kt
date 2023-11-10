package com.gunpang.ui.app.screen.bodyComposition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
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
import com.gunpang.ui.app.common.ContentsNoRecord
import com.gunpang.ui.app.common.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyCompositionScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = {
            TopBar(
            navController = navController,
            title = "체성분 분석"
            ) },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        it -> Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = Color.White),
            color = Color.White
        ) {
            // TODO : 채성분 화면 구성
            ContentsNoRecord( reason = "추후 개발...")
        }
    }
}