package com.gunpang.ui.app.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.common.R
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.app.common.TopBar
import com.gunpang.ui.theme.Gray300
import com.gunpang.ui.theme.Gray50
import com.gunpang.ui.theme.Gray500
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    ){
                    PrevArrowIcon(prev=1, tint= Gray500)
                    CharacterInfoPreview()
                    NextArrowIcon(next=1, tint= Gray500)
                }
                Spacer(modifier = Modifier.height(10.dp))
                TodayInfo(modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .fillMaxHeight(0.8f))
            }
        }

    }
}

@Composable
fun TodayInfo(modifier: Modifier) {
    Box(modifier= modifier,
        contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween){
                Text(
                    text = "운동",
                    fontFamily = gmarketsansBold,
                    textAlign = TextAlign.Left,
                    fontSize = 20.sp,
                    color = Gray900
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(
                    text = "아직안했어..",
                    fontFamily = gmarketsansBold,
                    textAlign = TextAlign.Right,
                    fontSize = 20.sp,
                    color = Gray900
                )
            }

            Row(horizontalArrangement = Arrangement.SpaceBetween){
                Text(
                    text = "수면",
                    fontFamily = gmarketsansBold,
                    textAlign = TextAlign.Left,
                    fontSize = 20.sp,
                    color = Gray900
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(
                    text = "23:00 ~ 06:00",
                    fontFamily = gmarketsansBold,
                    textAlign = TextAlign.Right,
                    fontSize = 20.sp,
                    color = Gray900
                )
            }

            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,){
                Text(
                    text = "식사",
                    fontFamily = gmarketsansBold,
                    textAlign = TextAlign.Left,
                    fontSize = 20.sp,
                    color = Gray900
                )
                Spacer(modifier = Modifier.width(32.dp))
                TodayFoodInfo()
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun GoalListPreview() {
    // 목표 보여주기
    Box(
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
            .width(192.dp),
        contentAlignment = Alignment.Center
    ){
        Column {
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
                ){
                AvatarName(name="양부장님")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier
                .height(196.dp)
                .padding(start = 4.dp, end = 4.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.avatar_cat),
                    contentDescription = "Character Image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit,
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Level()
                ProgressBar()
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun ProgressBar(progress: Float = 0.3f){
    LinearProgressIndicator(
        modifier= Modifier
            .height(15.dp)
            .padding(start = 4.dp)
            .clip(Shapes.extraLarge),
        progress = progress,
        color = Green500,
        trackColor = Gray300,
    )
}
@Composable
@Preview()
fun AvatarName(name: String = "삐약이"){
    Box(modifier = Modifier
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

@Composable
fun Level(level: Int = 1){
    Box(modifier = Modifier
        .width(48.dp)){
        Text(
            text = "Lv.$level",
            fontFamily = gmarketsansBold,
            textAlign = TextAlign.Left,
            fontSize = 20.sp,
            color = Gray900
        )
    }

}
@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun PrevArrowIcon(prev: Int = 1, tint: Color = Gray500){
    IconButton(
        onClick = {  },
        enabled = prev != -1,
        ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_prev_arrow),
            contentDescription = "이전 아바타 보기",
            tint = tint)
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun NextArrowIcon(next: Int = 1, tint: Color = Gray500){
    IconButton(onClick = { /*TODO*/ } ){
        Icon(
            painter= painterResource(id = R.drawable.ic_next_arrow),
            contentDescription = "다음 아바타 보기",
            tint = tint
        )
    }
}

@Composable
@Preview(
    widthDp = 128,
    heightDp = 42,
    showBackground = true,
    backgroundColor = 0xFFFFFFFF)
fun TodayFoodInfo(){
    Row(modifier = Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.food_healthy_salad),
            contentDescription = "식사 아이콘",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.question_mark),
            contentDescription = "식사 아이콘",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.food_bad_fried),
            contentDescription = "식사 아이콘",
        )
    }
}