package com.gunpang.ui.app.screen.main.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansLight
import java.time.LocalDate

@Composable
fun LivingDate(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(28.dp),
    startedDate: String,
    finishedDate: String,
){
    Text(
        modifier = modifier.padding(top = 4.dp),
        text = "$startedDate - $finishedDate",
        fontFamily = gmarketsansLight,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        color = Gray900
    )
}
