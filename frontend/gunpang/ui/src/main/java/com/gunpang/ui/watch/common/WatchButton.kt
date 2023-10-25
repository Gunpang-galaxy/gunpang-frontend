package com.gunpang.ui.app.watch.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.ui.theme.Gray800

import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Navy200
import com.gunpang.ui.theme.galmuri
@Composable
fun WatchButton(text: String, color: Color = Navy200, textColor: Color = Gray900, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(color),
        border = BorderStroke(2.dp, Gray800),
        //contentPadding = PaddingValues(top=6.dp, bottom = 6.dp),
        //modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 25.sp,
            fontFamily = galmuri
        )
    }
}