package com.gunpang.ui.app.screen.main.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.gunpang.common.R
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.code.DayCode
import com.gunpang.domain.entity.AvatarGoal
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansBold

@Composable
fun AvatarGoal(
    modifier : Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    avatarStatus: AvatarStatusCode = AvatarStatusCode.ALIVE,
    goal: AvatarGoal
    ){

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        // 목표 배경 이미지
        Image(
            modifier= Modifier.fillMaxWidth(),
            painter = painterResource(id = when{
                avatarStatus == AvatarStatusCode.ALIVE -> R.drawable.goal_ing
                avatarStatus == AvatarStatusCode.DEAD -> R.drawable.goal_fail
                avatarStatus == AvatarStatusCode.GRADUATED -> R.drawable.goal_success
                else -> R.drawable.goal_ing
            }),
            contentDescription = "목표 배경 이미지",
            contentScale = ContentScale.Fit
        )
        // 목표 텍스트
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ){
            Text(
                text= exerciseGoal(goal.exerciseDay, goal.exerciseTime),
                textAlign = TextAlign.Center,
                fontFamily = gmarketsansBold,
                fontSize = 20.sp,
                color = Gray900,
            )
            Text(
                text= "${goal.sleepStart} - ${goal.sleepEnd}",
                textAlign = TextAlign.Center,
                fontFamily = gmarketsansBold,
                fontSize = 20.sp,
                color = Gray900,
            )
            Text(
                text= goal.foodGoal,
                textAlign = TextAlign.Center,
                fontFamily = gmarketsansBold,
                fontSize = 20.sp,
                color = Gray900,
            )
        }
    }
}

fun exerciseGoal(
    exerciseDay : List<DayCode>,
    exerciseTime : String
) : String {
    var goalText = ""
    exerciseDay.forEachIndexed {index, day ->
        if(index == exerciseDay.size - 1)
            goalText += "${day.kor}요일 ${exerciseTime}분 운동하기"
        else
            goalText += "${day.kor}, "
    }
    return goalText
}

