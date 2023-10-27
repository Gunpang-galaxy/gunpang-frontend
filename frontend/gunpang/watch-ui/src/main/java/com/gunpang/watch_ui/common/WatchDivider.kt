package com.gunpang.watch_ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import com.gunpang.watch_ui.theme.Navy200

@Composable
fun WatchDivider(fraction: Float = 0.8f, thickness:Dp=4.dp) {
    Divider(
        color = Navy200,
        modifier = Modifier
            .fillMaxWidth(fraction = fraction)
            .clip(RoundedCornerShape(size = 100.dp)),
        thickness= thickness,

    )
}