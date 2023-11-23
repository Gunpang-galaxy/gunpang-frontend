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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.R
import com.gunpang.common.code.MealRecordCode
import com.gunpang.domain.entity.AppAvatarAliveContent
import com.gunpang.domain.entity.AppAvatarDeadContent
import com.gunpang.domain.entity.AppAvatarGraduatedContent
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansBold

@Composable
fun AvatarTodayInfo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    contents: AppAvatarAliveContent
){
    Box(
        modifier  = modifier.padding(top = 60.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            TodayInfoDetail(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                title = "운동",
                content = when{
                    contents.exerciseTime == "00시간 00분" -> "운동 안했네.."
                    else -> contents.exerciseTime
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            TodayInfoDetail(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp),
            title = "수면",
            content = when{
                contents.sleepAt == "-1" || contents.awakeAt == "-1" -> "기록이 안됐네.."
                else -> "${contents.sleepAt} - ${contents.awakeAt}"
            }
            )
            Spacer(modifier = Modifier.height(12.dp))
            TodayInfoDetail(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                title = "식사",
                content = listOf(
                    contents.breakfastFoodType,
                    contents.lunchFoodType,
                    contents.dinnerFoodType
                )
            )
        }

    }
}

@Composable
fun AvatarDeathInfo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    deathSign: String,
){
    // 이전 아바타가 죽었고, 왜 죽었는지
    Text(
        modifier = modifier,
        text = deathSign,
        fontFamily = gmarketsansBold,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = Gray900
    )

}
@Composable
fun FinishedAvatarInfo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    statusCode : Enum<AvatarStatusCode> = AvatarStatusCode.GRADUATED,
    startedDate: String,
    finishedDate: String,
    contents: Any
){
    // 이전 아바타가 졸업했고, 얼마나 목표를 달성했는지
    Box(modifier = modifier,
        contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()){

            LivingDate(
                startedDate=startedDate,
                finishedDate=finishedDate
            )
            var finishedInfoModifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 32.dp)
            when {
                statusCode == AvatarStatusCode.DEAD ->
                    AvatarDeathInfo(
                        modifier = finishedInfoModifier,
                        deathSign = (contents as AppAvatarDeadContent).deathCause.status
                    )

                else ->
                    AvatarGraduatedInfo(
                        modifier = finishedInfoModifier,
                        dateDuration = 28,
                        exerciseDate = 28,
                        exerciseTimes = (contents as AppAvatarGraduatedContent).exerciseSuccessCnt,
                        wellSleepTimes = contents.sleepSuccessCnt,
                        mealRecordTimes = contents.foodSuccessCnt
                    )
                // dateDuration = (finishedDate.time - startedDate.time) / (24 * 60 * 60 * 1000)
            }
        }

    }
}

@Composable
fun AvatarGraduatedInfo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    dateDuration: Int,
    exerciseDate : Int,
    exerciseTimes : Int,
    wellSleepTimes : Int,
    mealRecordTimes : Int
    ){

    Box(
        modifier = modifier,
        ){
        Column{
            goalProcessOneLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                IconImage = R.drawable.dumbell,
                totalDate = exerciseDate,
                goalDate = exerciseTimes
            )
            Spacer(modifier = Modifier.height(8.dp))
            goalProcessOneLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                IconImage = R.drawable.bed,
                totalDate = dateDuration,
                goalDate = wellSleepTimes
            )
            Spacer(modifier = Modifier.height(8.dp))
            goalProcessOneLine(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                IconImage = R.drawable.food_onigiri,
                totalDate = dateDuration,
                goalDate = mealRecordTimes
            )
        }
    }
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    widthDp = 390,
    heightDp = 36
)
@Composable
fun goalProcessOneLine(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    IconImage: Int = R.drawable.dumbell,
    totalDate : Int = 28,
    goalDate : Int = 10,
    ){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
        ){
        Row(modifier = Modifier
            .fillMaxWidth(0.6f)
            .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Image(
                painter = painterResource(id = IconImage),
                contentDescription = "목표 아이콘",
                contentScale = ContentScale.Fit
            )

            Text(
                text = "$goalDate / $totalDate 일",
                fontFamily = gmarketsansBold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Gray900
            )
        }
    }

}

@Composable
fun TodayInfoDetail(
    modifier: Modifier,
    title: String,
    content: Any,
    ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(
            text = title,
            fontFamily = gmarketsansBold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Gray900
        )
        Spacer(modifier = Modifier.width(32.dp))
        when {
            content is String ->
                Text(
                    text = content,
                    fontFamily = gmarketsansBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
            content is List<*> ->
                ShowMealRecord(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), content as List<MealRecordCode>)
            else ->
                Text(
                    text = "잘못된 정보입니다.",
                    fontFamily = gmarketsansBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Gray900
                )
        }

    }
}

@Composable
fun ShowMealRecord(
    modifier: Modifier,
    content: List<MealRecordCode>) {
    Row(
        modifier = modifier
    ){
        content.forEachIndexed { index, mealRecordCode ->
            Image(
                modifier = Modifier
                    .height(36.dp)
                    .width(36.dp),
                painter = painterResource(id = mealRecordCode.imageId),
                contentDescription = "오늘 기록",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


