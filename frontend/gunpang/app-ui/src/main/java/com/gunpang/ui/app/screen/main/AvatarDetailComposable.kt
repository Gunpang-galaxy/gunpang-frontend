package com.gunpang.ui.app.screen.main

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
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansBold
import com.gunpang.ui.theme.gmarketsansLight
import java.time.LocalDate
import java.util.Date

@Composable
@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    widthDp = 390,
    heightDp = 270
)
fun AvatarTodayInfo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    exerciseTime : Int = 0,
    sleepTime : String = "00:00 - 06:00",
    mealRecord : List<MealRecordCode> = listOf(
        MealRecordCode.NORMAL,
        MealRecordCode.NOT_RECORD,
        MealRecordCode.BAD
    ),
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
                    .height(36.dp),
                title = "운동",
                content = "아직 안함")
            Spacer(modifier = Modifier.height(8.dp))
            TodayInfoDetail(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                title = "수면",
                content = "23:30 - 06:00")
            Spacer(modifier = Modifier.height(8.dp))
            TodayInfoDetail(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp),
                title = "식사",
                content = mealRecord)
        }
    }
}

@Composable
@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    widthDp = 390,
    heightDp = 270
    )
fun AvatarDeathInfo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    deathSign: String = "수면 부족",
    startedDate: LocalDate = LocalDate.now(),
    finishedDate: LocalDate = LocalDate.now(),
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
@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    widthDp = 390,
    heightDp = 270
)
fun FinishedAvatarInfo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    statusCode : Enum<AvatarStatusCode> = AvatarStatusCode.GRADUATED,
    startedDate: LocalDate = LocalDate.now(),
    finishedDate: LocalDate = LocalDate.now(),
){
    // 이전 아바타가 졸업했고, 얼마나 목표를 달성했는지
    Box(modifier = modifier,
        contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()){

            LivingDate(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp))
            var finishedInfoModifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 32.dp)
            when {
                statusCode == AvatarStatusCode.DEAD ->
                    AvatarDeathInfo(
                        modifier = finishedInfoModifier,
                        startedDate = startedDate,
                        finishedDate = finishedDate,
                    )

                else ->
                    AvatarGraduatedInfo(
                        modifier = finishedInfoModifier,
                    )
                // dateDuration = (finishedDate.time - startedDate.time) / (24 * 60 * 60 * 1000)
            }
        }

    }
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    widthDp = 390,
    heightDp = 270
)
@Composable
fun AvatarGraduatedInfo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    dateDuration: Int = 28,
    exerciseDate : Int = 10,
    exerciseTimes : Int = 8,
    wellSleepTimes : Int = 20,
    mealRecordTimes : Int = 24
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
                painter = painterResource(id = mealRecordCode.image),
                contentDescription = "오늘 기록",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    widthDp = 390,
    heightDp = 28
)
fun LivingDate(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(28.dp),
    startedDate: LocalDate = LocalDate.now(),
    finishedDate: LocalDate = LocalDate.now(),
){
        Text(
            modifier = modifier.padding(top = 4.dp,),
            text = "$finishedDate - $startedDate",
            fontFamily = gmarketsansLight,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Gray900)
}