package com.gunpang.ui.app.screen.bodyComposition

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gunpang.data.manager.AppHealthConnectManager
import com.gunpang.common.R
import com.gunpang.domain.app.bodyComposition.BodyCompositionViewModel
import com.gunpang.domain.entity.BodyCompositionInfo
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.app.common.ContentsNoRecord
import com.gunpang.ui.app.common.TopBar
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsans
import com.gunpang.ui.theme.gmarketsansBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyCompositionScreen(
    navController: NavController,
    healthConnectManager: AppHealthConnectManager,
    bodyCompositionViewModel: BodyCompositionViewModel
) {

    LaunchedEffect(true){
        Log.d("BodyCompositionScreen", "LaunchedEffect")
        bodyCompositionViewModel.init()
    }

    Log.d("BodyCompositionScreen", "화면 진입")

    Scaffold(
        topBar = {
            TopBar(
            navController = navController,
            title = "체성분 분석",
            ) },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        it -> Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = Color.White),
            color = Color.White
        ) {
            // TODO : 채성분 화면 구성
//            ContentsNoRecord( reason = "추후 개발...")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(90.dp))

            val bodyCompositionModifier = Modifier
                .fillMaxWidth()
                .height(70.dp)

            val showPrevValues = bodyCompositionViewModel.prevValuesExist()

            if (showPrevValues) {
                BodyCompositionInfoItemWithPrev(
                    modifier = bodyCompositionModifier,
                    fieldName = "체중",
                    fieldValuePrev = bodyCompositionViewModel.prevWeight,
                    fieldValueCur = bodyCompositionViewModel.curWeight
                )
                BodyCompositionInfoItemWithPrev(
                    modifier = bodyCompositionModifier,
                    fieldName = "체지방량",
                    fieldValuePrev = bodyCompositionViewModel.prevFatMass,
                    fieldValueCur = bodyCompositionViewModel.curFatMass
                )
                BodyCompositionInfoItemWithPrev(
                    modifier = bodyCompositionModifier,
                    fieldName = "체지방률",
                    fieldValuePrev = bodyCompositionViewModel.prevFatMassPct,
                    fieldValueCur = bodyCompositionViewModel.curFatMassPct
                )
                BodyCompositionInfoItemWithPrev(
                    modifier = bodyCompositionModifier,
                    fieldName = "BMI",
                    fieldValuePrev = bodyCompositionViewModel.prevBMI,
                    fieldValueCur = bodyCompositionViewModel.curBMI
                )
            } else {
                BodyCompositionInfoItemWithOutPrev(
                    modifier = bodyCompositionModifier,
                    fieldName = "체중",
                    fieldValueCur = bodyCompositionViewModel.curWeight
                )
                BodyCompositionInfoItemWithOutPrev(
                    modifier = bodyCompositionModifier,
                    fieldName = "체지방량",
                    fieldValueCur = bodyCompositionViewModel.curFatMass
                )
                BodyCompositionInfoItemWithOutPrev(
                    modifier = bodyCompositionModifier,
                    fieldName = "체지방률",
                    fieldValueCur = bodyCompositionViewModel.curFatMassPct
                )
                BodyCompositionInfoItemWithOutPrev(
                    modifier = bodyCompositionModifier,
                    fieldName = "BMI",
                    fieldValueCur = bodyCompositionViewModel.curBMI
                )
            }
        }
    }
    }
}

@Composable
fun BodyCompositionInfoItemWithPrev(
    modifier: Modifier = Modifier,
    fieldName: String,
    fieldValuePrev: String,
    fieldValueCur: String
) {
    val prevValue = fieldValuePrev.toDoubleOrNull() ?: 0.0
    val curValue = fieldValueCur.toDoubleOrNull() ?: 0.0

    Column(
        modifier = Modifier
            .padding(
                start = 50.dp,
                top = 5.dp,
                end = 40.dp,
                bottom = 30.dp
            )
    ) {
        Text(
            text = fieldName,
            fontFamily = gmarketsansBold,
            color = Gray900,
            fontSize = 20.sp,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = getFormattedValue(fieldName, fieldValuePrev),
                fontFamily = gmarketsans,
                color = Gray900,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 20.dp)
            )

            Column(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    modifier = Modifier
                        .size(70.dp)
                        .offset(y = 10.dp)
                )
                val difference = String.format("%.1f", curValue - prevValue).toDouble()
                val formattedDifference = getFormattedValue(fieldName, difference.toString())
                val sign = if (difference >= 0) "+" else ""
                Text(
                    text = "$sign$formattedDifference",
                    fontFamily = gmarketsans,
                    color = Gray900,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.offset(x = 5.dp, y = -45.dp)
                )
            }

            Text(
                text = getFormattedValue(fieldName, fieldValueCur),
                fontFamily = gmarketsans,
                color = Gray900,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(end = 20.dp)
            )
        }
    }
}

@Composable
fun BodyCompositionInfoItemWithOutPrev(
    modifier: Modifier = Modifier,
    fieldName: String,
    fieldValueCur: String
) {
    val curValue = fieldValueCur.toDoubleOrNull() ?: 0.0

    Column(
        modifier = Modifier
            .padding(
                start = 50.dp,
                top = 5.dp,
                end = 40.dp,
                bottom = 30.dp
            )
    ) {
        Text(
            text = fieldName,
            fontFamily = gmarketsansBold,
            color = Gray900,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = getFormattedValue(fieldName, fieldValueCur),
                fontFamily = gmarketsans,
                color = Gray900,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 20.dp)
            )

        }
    }
}

fun getFormattedValue(fieldName: String, value: String): String {
    return when (fieldName) {
        "체중", "체지방량" -> String.format("%skg", value)
        "체지방률" -> String.format("%s%%", value)
        "BMI" -> value + "    "
        else -> value
    }
}

fun BodyCompositionViewModel.prevValuesExist(): Boolean {
    Log.d("BodyCompositionScreen", "prevValuesExist 확인")
    return prevWeight != "0.0" || prevFatMass != "0.0" || prevFatMassPct != "0.0" || prevBMI != "0.0"
}