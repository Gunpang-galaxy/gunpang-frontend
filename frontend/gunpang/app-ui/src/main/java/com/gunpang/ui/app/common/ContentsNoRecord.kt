package com.gunpang.ui.app.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.common.R
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansBold
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

/**
 * @author 권기윤
 * 기록 없을 때 Composable
 */
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF,
)
fun ContentsNoRecord(
    modifier: Modifier = Modifier.fillMaxSize(),
    reason: String = "기록이 없어요..",
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        // TODO : 기록이 없을 때 화면 구성
        Column(
            modifier = Modifier.fillMaxWidth(0.6f),
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            Image(
                painter = painterResource(id = R.drawable.loading_cloud),
                contentDescription = "기록이 없어요",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = reason,
                fontSize = 25.sp,
                fontFamily = gmarketsansBold,
                textAlign = TextAlign.Center,
                color = Gray900
            )
        }
    }
}