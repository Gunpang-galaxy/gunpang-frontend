package com.gunpang.ui.app.common

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.gunpang.common.R
import com.gunpang.common.navigation.AppNavItem

@Composable
fun BottomNavBar(
    navController: NavController,
    containerColor: Color = Color.White,
    contentColor: Color = Color.White
) {
    NavigationBar(
        containerColor = containerColor,
        contentColor = contentColor
    ) {

        Log.d("[바텀바 현재 스크린]", navController.currentDestination?.route!!)
        Log.d("[바텀바 현재 스크린]", navController.currentDestination?.route!!)

        NavigationBarItem(
            selected = navController.currentDestination?.route!! == AppNavItem.CalenderScreen.routeName,
            onClick = {
                Log.d("[바텀바 달력 클릭]", AppNavItem.CalenderScreen.routeName)
                if(navController.currentDestination?.route != AppNavItem.CalenderScreen.routeName){
                    navController.navigate(AppNavItem.CalenderScreen.routeName)
                }
            },
            icon = { Box(){
                Icon(painterResource(id = R.drawable.ic_bottom_nav_cal), contentDescription = "달력 화면")
            } })
        NavigationBarItem(
            selected = navController.currentDestination?.route == AppNavItem.MainScreen.routeName,
            onClick = {
                Log.d("[바텀바 메인 클릭]", AppNavItem.MainScreen.routeName)
                if(navController.currentDestination?.route != AppNavItem.MainScreen.routeName){
                    navController.navigate(AppNavItem.MainScreen.routeName)
                }
            },
            icon = { Box(){
                Icon(painterResource(id = R.drawable.ic_bottom_nav_home),
                    contentDescription = "메인 화면")

            } },
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == AppNavItem.BodyCompositionScreen.routeName,
            onClick = {
                Log.d("[바텀바 체성분 클릭]", AppNavItem.BodyCompositionScreen.routeName)
                if(navController.currentDestination?.route != AppNavItem.BodyCompositionScreen.routeName){
                    navController.navigate(AppNavItem.BodyCompositionScreen.routeName)
                }
            },
            icon = { Box(){
                Icon(painterResource(id = R.drawable.ic_bottom_nav_chart),
                    contentDescription = "체성분 화면")
            } })
    }
}