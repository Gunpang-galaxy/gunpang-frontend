package com.gunpang.ui.app.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Navy200
import com.gunpang.ui.theme.Shapes
import com.gunpang.ui.theme.gmarketsans

@Composable
@Preview(showBackground = true)
fun CommonButton(
    text: String = "연동하기",
    alpha: Float = 1f,
    enabled: Boolean = true,
    onClick: () -> Unit = { }
){
    Box(modifier = Modifier.width(203.dp).height(58.dp)) {
        if(!enabled) return
        Button(
            onClick = onClick,
            shape = Shapes.medium,
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha),
            colors = ButtonDefaults.buttonColors(Navy200),
        ) {
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = gmarketsans,
                fontWeight = FontWeight.Medium,
                color = Gray900,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Visible,
            )
        }
    }
}
