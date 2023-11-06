package com.gunpang.watch_ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gunpang.watch_ui.theme.Gray900
import com.gunpang.watch_ui.theme.GunpangTheme

@Composable
fun GunpangScreenWrapper(
    content: @Composable () -> Unit
) {
    GunpangTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray900)
                .padding(15.dp),
            contentAlignment = Alignment.Center,
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