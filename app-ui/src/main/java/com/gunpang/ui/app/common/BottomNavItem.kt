package com.gunpang.ui.app.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.gunpang.common.R


data class BottomNavItem(
    val label: String,
    val route: String,
    val imageId: Int,
    val description: String = ""
)

var bottomNavItems = listOf<BottomNavItem>(
    BottomNavItem(
        label= "Calender",
        route= "main",
        imageId= R.drawable.ic_bottom_nav_cal,
    ),
    BottomNavItem(
        label="Main",
        route= "main",
        imageId= R.drawable.ic_bottom_nav_home,
    ),
    BottomNavItem(
        label="Chart",
        route= "main",
        imageId= R.drawable.ic_bottom_nav_chart,
    ),
)