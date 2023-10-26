package com.gunpang.ui.app.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.common.R
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.app.common.TopBar
import com.gunpang.ui.theme.Gray300
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Green500
import com.gunpang.ui.theme.Shapes
import com.gunpang.ui.theme.gmarketsansBold
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreenPreview(){
    Scaffold(
        topBar={ TopBar() },
        containerColor= Color.White,
        bottomBar = { BottomNavBar() },
    ){
        paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .padding(paddingValues),
            color = Color.White
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Spacer(modifier = Modifier.height(20.dp))
                GoalListPreview()
                Spacer(modifier = Modifier.height(60.dp))
                CharacterInfoPreview()

            }
        }

    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun GoalListPreview() {
    // 목표 보여주기
    Box(
        modifier = Modifier.
            fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.goal_ing),
            contentDescription = "Background Image",
            contentScale = ContentScale.Fit,
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "월, 수 운동하기",
                textAlign = TextAlign.Center,
                fontFamily = gmarketsansBold,
                fontSize = 20.sp,
                color = Gray900,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "23:30 - 06:20",
                textAlign = TextAlign.Center,
                fontFamily = gmarketsansBold,
                fontSize = 20.sp,
                color = Gray900,
            )
            Spacer(modifier = Modifier.height(12.dp))
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


@Preview(
    widthDp = 128,
    heightDp = 256,
    showBackground = true,
    backgroundColor = 0xFFFFFFFF)
@Composable
fun CharacterInfoPreview(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
        )
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

                ){
                Text(
                    text = "Lv4",
                    fontFamily = gmarketsansBold,
                    textAlign = TextAlign.Left,
                    fontSize = 20.sp,
                    color = Gray900
                )
                Text(
                    text = "현재팡이",
                    fontFamily = gmarketsansBold,
                    textAlign = TextAlign.Right,
                    fontSize = 20.sp,
                    color = Gray900
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .padding(start= 4.dp, end= 4.dp),
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.avatar_chick),
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Fit,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            LinearProgressIndicator(
                modifier= Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .padding(start = 4.dp, end = 4.dp)
                    .clip(Shapes.extraLarge),
                progress = 0.5f,
                color = Green500,
                trackColor = Gray300,
            )
        }

    }
