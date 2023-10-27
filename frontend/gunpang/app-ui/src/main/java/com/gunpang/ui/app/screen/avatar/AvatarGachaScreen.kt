package com.gunpang.ui.app.screen.avatar

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object AvatarEggScreen : Screen("avatarEgg")
    object NameAvatarScreen : Screen("nameAvatar")
}

@Composable
fun AvatarGatcha() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AvatarEggScreen.route
    ) {
        composable(Screen.AvatarEggScreen.route) {
            AvatarEgg(navController)
        }
        composable(Screen.NameAvatarScreen.route) {
            NameAvatar()
        }
    }
}
