package com.gunpang.ui.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.theme.GunpangTheme
import com.gunpang.ui.app.common.topBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AppMain(){
    GunpangTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),

        ){
            Scaffold(
                topBar={ topBar() },
                containerColor= Color.White,

                bottomBar = { BottomNavBar() }
            ) {
                /**
                 * TODO
                 * 목표 설정 컴포저블
                 * 캐릭터 컴포저블
                 * */
            }
        }
    }
}