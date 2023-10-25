package com.gunpang.ui.app.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gunpang.common.R

@Composable
@Preview(
    showBackground = true)
fun BottomNavBar(
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {  },
            icon = { Box(){
                Icon(painterResource(id = R.drawable.ic_bottom_nav_cal), contentDescription = "달력 화면")
            } })
        NavigationBarItem(
            selected = true,
            onClick = {  },
            icon = { Box(){
                Icon(painterResource(id = R.drawable.ic_bottom_nav_home),
                    contentDescription = "메인 화면")

            } },
        )
        NavigationBarItem(selected = false,
            onClick = {  },
            icon = { Box(){
                Icon(painterResource(id = R.drawable.ic_bottom_nav_chart),
                    contentDescription = "체성분 화면")
            } })
    }
}