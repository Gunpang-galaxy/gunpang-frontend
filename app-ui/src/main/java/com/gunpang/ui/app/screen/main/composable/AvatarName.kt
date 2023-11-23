package com.gunpang.ui.app.screen.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.ui.theme.Gray50
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Shapes
import com.gunpang.ui.theme.gmarketsansBold

@Composable
fun AvatarName(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
    name: String
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(40.dp)
                .clip(Shapes.medium)
                .background(color = Gray50),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                text = name,
                fontFamily = gmarketsansBold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Gray900
            )
        }
    }
}