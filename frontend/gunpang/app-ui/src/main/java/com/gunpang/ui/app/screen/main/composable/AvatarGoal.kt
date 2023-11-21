package com.gunpang.ui.app.screen.main.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.common.R
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.code.DayCode
import com.gunpang.domain.entity.AvatarGoal
import com.gunpang.ui.app.util.getTimeFromString
import com.gunpang.ui.theme.Gray500
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansBold

@Composable
fun AvatarGoal(
    modifier : Modifier,
    avatarStatus: AvatarStatusCode = AvatarStatusCode.ALIVE,
    goal: AvatarGoal
    ){
    Log.d("아바타 목표", goal.toString())
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
            verticalArrangement = Arrangement.Center,
        ){
            var modifier : Modifier = Modifier.fillMaxWidth(0.55f).height(32.dp)
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                ) {
                // 아이콘
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.run),
                    contentDescription = "운동 아이콘"
                )
                Spacer(modifier= Modifier.width(16.dp))
                Text(
                    text= getExerciseDay(goal.exerciseDay),
                    textAlign = TextAlign.Right,
                    fontFamily = gmarketsansBold,
                    fontSize = 20.sp,
                )
            }
            Spacer(modifier= Modifier.height(8.dp))
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.muscle),
                    contentDescription = "운동 아이콘"
                )
                Spacer(modifier= Modifier.width(16.dp))
                Text(
                    text= "${getTimeFromString(goal.exerciseTime)} 운동하기",
                    fontFamily = gmarketsansBold,
                    textAlign = TextAlign.Right,
                    fontSize = 20.sp,
                    color = Gray900,
                )
            }
            Spacer(modifier= Modifier.height(8.dp))
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                ){
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.sleep),
                    contentDescription = "운동 아이콘"
                )
                Spacer(modifier= Modifier.width(16.dp))
                Text(
                    text= when {
                        goal.sleepStart != "" && goal.sleepEnd != "" ->
                            "${goal.sleepStart} - ${goal.sleepEnd}"
                        else ->
                            "아직 수면 시간 설정 안했어"
                    },
                    textAlign = TextAlign.Right,
                    fontFamily = gmarketsansBold,
                    fontSize = 20.sp,
                    color = Gray900,
                )
            }
        }
    }
}

fun getExerciseDay(
    exerciseDay: List<DayCode>
) : AnnotatedString {
    val allDay : List<String> = listOf("월", "화", "수", "목", "금", "토", "일")
    return buildAnnotatedString {
        allDay.forEach {day ->
            var dayCode : DayCode = DayCode.fromKor(day)
            withStyle(style = SpanStyle(color = if(exerciseDay.contains(dayCode)) Gray900 else Gray500)){
                append("$day ")
            }
        }
    }
}

