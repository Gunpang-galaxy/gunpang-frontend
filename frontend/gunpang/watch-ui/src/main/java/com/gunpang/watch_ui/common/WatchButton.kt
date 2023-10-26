package com.gunpang.watch_ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import com.gunpang.watch_ui.theme.Gray800
import com.gunpang.watch_ui.theme.Navy200
import com.gunpang.watch_ui.theme.galmuri


@Composable
fun WatchButton(text: String,  color: Color = Navy200, textColor :Color = Gray800, onClick: () -> Unit) {
    Button(
        onClick = { onClick },
        colors = ButtonDefaults.buttonColors(color),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(4.dp)  // 버튼 내부의 패딩 추가
            .border(2.dp, Gray800, CircleShape)
            .height(40.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontFamily = galmuri,
        )
    }
}