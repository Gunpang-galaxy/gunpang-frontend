package com.gunpang.ui.app.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.common.R
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.app.common.topBar
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansBold

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreenPreview(){
    Scaffold(
        topBar={ topBar() },
        containerColor= Color.White,
        bottomBar = { BottomNavBar() },
    ) { contentPadding -> GoalListPreview(modifier = Modifier.padding(contentPadding)) }
}


@Composable
fun GoalListPreview(modifier: Modifier) {
    // 목표 보여주기
    Box(
        modifier = modifier
            .width(348.dp)
            .height(116.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(1.0f)
                .height(115.dp),
            painter = painterResource(id = R.drawable.goal_ing),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillHeight,
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "월, 수 운동하기",
                textAlign = TextAlign.Center,
                fontFamily = gmarketsansBold,
                fontSize = 20.sp,
                color = Gray900,
            )
            Text(
                text = "23:30 - 06:20",
                textAlign = TextAlign.Center,
                fontFamily = gmarketsansBold,
                fontSize = 20.sp,
                color = Gray900,
            )
            Text(
                text = "식사 기록하기",
                textAlign = TextAlign.Center,
                fontFamily = gmarketsansBold,
                fontSize = 20.sp,
                color = Gray900,
            )
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CharacterInfoPreview(){
    Box(modifier = Modifier
        .padding(0.dp)
        .width(124.dp)
        .height(238.dp)){
        Column {
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
                Text(
                    text = "Lv4",
                    //textAlign = TextAlign.Left,
                    fontFamily = gmarketsansBold,
                    fontSize = 20.sp,
                    color = Gray900
                )
                Text(
                    text = "팡이",
                    //textAlign = TextAlign.Right,
                    fontFamily = gmarketsansBold,
                    fontSize = 20.sp,
                    color = Gray900
                )
            }
        }
    }
}