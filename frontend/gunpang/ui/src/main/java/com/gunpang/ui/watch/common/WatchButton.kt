package com.gunpang.ui.app.watch.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunpang.ui.theme.Gray800

import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Navy200
import com.gunpang.ui.theme.galmuri
@Composable
fun WatchButton(text: String, color: Color = Navy200, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(color),
        border = BorderStroke(2.dp, Gray800)
    ) {
        Text(
            text = text,
            color = Gray900,
            fontFamily = galmuri
        )
    }
}