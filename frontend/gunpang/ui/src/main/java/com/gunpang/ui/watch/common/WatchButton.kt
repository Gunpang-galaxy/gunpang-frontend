package com.gunpang.ui.watch.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Navy200
import com.gunpang.ui.theme.galmuri

@Composable
fun WatchButton(text: String,  color: Color = Navy200, textColor :Color = Gray900, onClick: () -> Unit) {
    Button(
        onClick = { onClick },
        colors = ButtonDefaults.buttonColors(color),
        modifier = Modifier
            .padding(4.dp)  // 버튼 내부의 패딩 추가

    ) {
        Text(
            text = text,
            color = textColor,
            fontFamily = galmuri,
        )
    }
}