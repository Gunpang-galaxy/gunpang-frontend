package com.gunpang.ui.app.screen.main

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
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansBold

@Composable
@Preview(
    heightDp = 200, showBackground = true)
fun AvatarGoal(
    modifier : Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    avatarStatus: AvatarStatusCode = AvatarStatusCode.ALIVE,
    goalList: List<String> =
        listOf<String>(
            "월, 수 운동하기",
            "23:30 - 06:00",
            "식사 기록하기"
            )
    ){
    // 목표 배경 이미지 설정
    var goalBackground:Int = when{
        avatarStatus == AvatarStatusCode.ALIVE -> R.drawable.goal_ing
        avatarStatus == AvatarStatusCode.DEAD -> R.drawable.goal_fail
        avatarStatus == AvatarStatusCode.GRADUATED -> R.drawable.goal_success
        else -> R.drawable.goal_ing
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        // 목표 배경 이미지
        Image(
            modifier= Modifier.fillMaxWidth(),
            painter = painterResource(id = goalBackground),
            contentDescription = "목표 배경 이미지",
            contentScale = ContentScale.Fit
        )
        // 목표 텍스트
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ){
            goalList.forEachIndexed { index, goalText ->
                Text(
                    text= goalText,
                    textAlign = TextAlign.Center,
                    fontFamily = gmarketsansBold,
                    fontSize = 20.sp,
                    color = Gray900,
                )

            }
        }
    }

}