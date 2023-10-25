package com.gunpang.ui.app.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector


data class BottomNavItem(
    val name: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

var bottomNavItems = listOf<BottomNavItem>(
    BottomNavItem(
        name="Home",
        route= "main",
        selectedIcon= Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    BottomNavItem(
        name="Chart",
        route= "main",
        selectedIcon= Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    BottomNavItem(
        name="Calender",
        route= "main",
        selectedIcon= Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
)