package com.gunpang.ui.app.watch.common

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text

@Composable
fun WatchChip(){

    Chip(
        onClick = { /* Do something */ },
        enabled = true,
        // When we have both label and secondary label present limit both to 1 line of text
        label = { Text(text = "Main label", maxLines = 1, overflow = TextOverflow.Ellipsis) },
        secondaryLabel = {
            Text(text = "secondary label", maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        icon = {
            Icon(
                painter = painterResource(id = androidx.core.R.drawable.notification_bg_low),
                contentDescription = "airplane",
                modifier = Modifier.size(ChipDefaults.IconSize)
                    .wrapContentSize(align = Alignment.Center),
            )
        }
    )
}