package com.gunpang.ui.app.common

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.gunpang.common.R
import com.gunpang.common.navigation.AppNavItem
import com.gunpang.ui.theme.Gray600
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsans

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    title: String,
    contentColor: Color = Color.White,
    titleContentColor: Color = Gray900,
    hasUndo : Boolean = false,
    iconTint: Color = Gray600,
){
    Log.d("[현재 스크린 이름]", navController.currentDestination?.route!!)
    Log.d("[현재 메뉴 title]", title)
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = contentColor,
            titleContentColor = titleContentColor,
        ),
        title = {
            Text(
                text = title,
                fontFamily = gmarketsans,
                maxLines = 1,
            )
        },
        navigationIcon = {
            if(hasUndo && navController.currentBackStackEntry != null){
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Backward",
                        tint = iconTint
                    )
                }
            }
        },
        actions = {
            /**
             * TODO : 사용자 화면으로 이동
             * TODO : 이미 설정 화면이라면 아이콘 버튼 없어야함
             * */
            if(navController.currentDestination?.route != AppNavItem.MyPageScreen.routeName){
                IconButton(onClick = { navController.navigate(AppNavItem.MyPageScreen.routeName) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_top_nav_setting),
                        contentDescription = "내정보 화면",
                        tint = iconTint
                    )
                }
            }

        }
    )
}