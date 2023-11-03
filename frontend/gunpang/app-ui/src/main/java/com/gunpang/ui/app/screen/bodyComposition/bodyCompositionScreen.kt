package com.gunpang.ui.app.screen.bodyComposition

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gunpang.domain.app.AppViewModel
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.app.common.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyCompositionScreen(
    navController: NavController,
    appViewModel: AppViewModel
) {
    Scaffold(
        topBar = {
            TopBar(
            navController = navController,
            title = "채성분 분석"
            ) },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        it -> Surface(
            modifier = Modifier.padding(it)
        ) {
            // TODO : 채성분 화면 구성
        }
    }
}