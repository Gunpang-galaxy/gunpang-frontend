package com.gunpang.ui.watch.common

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gunpang.ui.theme.Navy200
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable

@Composable
fun WatchDivider() {
    Divider(
        color = Navy200, thickness = 2.dp, modifier = Modifier
            .width(50.dp)
            .clip(
                RoundedCornerShape(100)
            )
    )
}