package com.gunpang.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.gunpang.ui.theme.galmuri

@Composable
fun WatchChip(label: String,  content: @Composable () -> Unit = {}) {
    Box(
        modifier = Modifier.padding(5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, fontFamily = galmuri)
            Spacer(modifier = Modifier.width(3.dp))
            content()  // TODO 부분에 Composable 표시
        }
    }
}