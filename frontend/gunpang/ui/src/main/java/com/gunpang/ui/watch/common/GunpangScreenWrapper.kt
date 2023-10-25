package com.gunpang.ui.app.watch.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.GunpangTheme

@Composable
fun GunpangScreenWrapper(
    content: @Composable () -> Unit
) {
    GunpangTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray900),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                content()
            }
        }
    }
}