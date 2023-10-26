package com.gunpang.watch_ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.gunpang.watch_ui.theme.Navy200

@Composable
fun WatchDivider(width: Dp = 50.dp) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .height(1.dp)
            .width(width)
            .background(Navy200)
    )
}