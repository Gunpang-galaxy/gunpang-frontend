package com.gunpang.ui.app.screen.calender

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.nextMonth
import com.kizitonwose.calendar.core.previousMonth
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.YearMonth
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.app.common.TopBar
import com.kizitonwose.calendar.compose.CalendarLayoutInfo
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.core.CalendarMonth
import kotlinx.coroutines.flow.filterNotNull
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.gunpang.domain.app.calendar.CalendarRecordViewModel
import com.gunpang.ui.app.common.ContentsNoRecord
import com.gunpang.ui.theme.Gray600
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Navy200
import com.gunpang.ui.theme.gmarketsansBold
import com.gunpang.ui.theme.gmarketsansLight

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalenderScreen(
    navController: NavController,
    calendarRecordViewModel: CalendarRecordViewModel,
) {

    Scaffold(
        topBar = {
            TopBar(navController = navController, title = "내 기록")
        },
        bottomBar = { BottomNavBar(navController) },
        containerColor = Color.White
    ) { it ->
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color.White),
            color = Color.White

        ) {
            val currentMonth = remember { YearMonth.now() }
            val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
            val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
            val daysOfWeek = remember { daysOfWeek(firstDayOfWeek = DayOfWeek.SUNDAY) }
            var selectedDate by remember {
                mutableStateOf<LocalDate?>(null)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally // 가로 중앙 정렬

            ) {
                val state = rememberCalendarState(
                    startMonth = startMonth,
                    endMonth = endMonth,
                    firstVisibleMonth = currentMonth,
                    firstDayOfWeek = daysOfWeek.first()
                )
                val coroutineScope = rememberCoroutineScope()
                val visibleMonth = rememberFirstMostVisibleMonth(state, viewportPercent = 90f)

                SimpleCalendarTitle(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
                    currentMonth = visibleMonth.yearMonth,
                    goToPrevious = {
                        coroutineScope.launch {
                            state.animateScrollToMonth(state.firstVisibleMonth.yearMonth.previousMonth)
                        }
                    },
                    goToNext = {
                        coroutineScope.launch {
                            state.animateScrollToMonth(state.firstVisibleMonth.yearMonth.nextMonth)
                        }
                    },
                )
                DaysOfWeekTitle(daysOfWeek = daysOfWeek)
                HorizontalCalendar(
                    modifier = Modifier
                        .testTag("Calendar")
                        .size(400.dp, 350.dp),
                    state = state,
                    userScrollEnabled = true,
                    //day.date로 다 요청 보내고 있는지 판단
                    //있으면 점표시
                    dayContent = { day ->
                        Day(day, isSelected = selectedDate == day.date) { day ->
                            selectedDate = if (selectedDate == day.date) null
                            else day.date
                            if (selectedDate == null)
                                calendarRecordViewModel.init()
                            if (selectedDate != null) {
                                calendarRecordViewModel.init()
                                calendarRecordViewModel.requestApi(selectedDate.toString())
                            }
                        }
                        //Log.d("selectedDate : ", selectedDate.toString());
                        Log.d("day.date : ", day.date.toString());
                    },
                )
                Spacer(modifier = Modifier.height(7.dp))
                if (!isValidRecord(calendarRecordViewModel))
                    ContentsNoRecord()
                else {
                    Column(
                        modifier = Modifier.padding(horizontal = 50.dp) // 좌우에 패딩 추가
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "하루 요약",
                            textAlign = TextAlign.Left,
                            fontFamily = gmarketsansBold,
                            fontSize = 20.sp,
                            color = Gray900,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        //운동기록 출력
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End

                        ) {
                            Text(
                                text = when {
                                    isValidRecord(calendarRecordViewModel) ->
                                        "운동"

                                    else -> "00시간 00분"
                                },
                                textAlign = TextAlign.Center,
                                fontFamily = gmarketsansLight,
                                fontSize = 20.sp,
                                color = Gray900,
                            )
                            Spacer(modifier = Modifier.width(85.dp))
                            Text(
                                text = when {
                                    isValidRecord(calendarRecordViewModel) ->
                                        "${calendarRecordViewModel.exerciseTime}"

                                    else -> ""
                                },
                                textAlign = TextAlign.Center,
                                fontFamily = gmarketsansLight,
                                fontSize = 20.sp,
                                color = Gray900,
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        //수면기록 출력
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End

                        ) {
                            if (calendarRecordViewModel.sleepAt == "-1" && calendarRecordViewModel.awakeAt == "-1")
                                Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = when {
                                    isValidRecord(calendarRecordViewModel) ->
                                        " 수면"

                                    else -> ""
                                },
                                textAlign = TextAlign.Center,
                                fontFamily = gmarketsansLight,
                                fontSize = 20.sp,
                                color = Gray900,
                            )
                            if (calendarRecordViewModel.sleepAt == "-1" && calendarRecordViewModel.awakeAt == "-1")
                                Spacer(modifier = Modifier.width(60.dp))
                            else
                                Spacer(modifier = Modifier.width(66.dp))
                            Text(
                                text = when {
                                    (isValidRecord(calendarRecordViewModel) && calendarRecordViewModel.sleepAt != "-1" && calendarRecordViewModel.awakeAt != "-1") ->
                                        "${calendarRecordViewModel.awakeAt} - ${calendarRecordViewModel.sleepAt}"

                                    else -> "00:00 - 00:00"
                                },
                                textAlign = TextAlign.Center,
                                fontFamily = gmarketsansLight,
                                fontSize = 20.sp,
                                color = Gray900,
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        //식사기록 출력
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End

                        ) {
                            Text(
                                text = when {
                                    isValidRecord(calendarRecordViewModel) -> "식사"
                                    else -> ""
                                },
                                textAlign = TextAlign.Center,
                                fontFamily = gmarketsansLight,
                                fontSize = 20.sp,
                                color = Gray900,
                            )
                            Spacer(modifier = Modifier.width(68.dp))
                            Log.d("아침 음식 타입", calendarRecordViewModel.breakfastFoodType.foodType)
                            Image(
                                painter = painterResource(id = calendarRecordViewModel.breakfastFoodType.imageId),
                                contentDescription = "아침 기록", // 이 부분은 필요에 따라 설정
                                modifier = Modifier.size(40.dp), // 아이콘의 크기 설정                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 er = Modifier.size(40.dp), // 아이콘의 크기 설정
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Log.d("점심 음식 타입", calendarRecordViewModel.lunchFoodType.foodType)
                            Image(
                                painter = painterResource(id = calendarRecordViewModel.lunchFoodType.imageId),
                                contentDescription = "점심 기록", // 이 부분은 필요에 따라 설정
                                modifier = Modifier.size(40.dp), // 아이콘의 크기 설정
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Log.d("저녁 음식 타입", calendarRecordViewModel.dinnerFoodType.foodType)
                            Image(
                                painter = painterResource(id = calendarRecordViewModel.dinnerFoodType.imageId),
                                contentDescription = "저녁 기록", // 이 부분은 필요에 따라 설정
                                modifier = Modifier.size(40.dp), // 아이콘의 크기 설정
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    //운동기록 출력
                    Column(
                        modifier = Modifier.padding(horizontal = 50.dp) // 좌우에 패딩 추가
                    ) {
                        Text(
                            text = "운동 기록",
                            textAlign = TextAlign.Left,
                            fontFamily = gmarketsansBold,
                            fontSize = 20.sp,
                            color = Gray900,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            //horizontalArrangement = Arrangement.End
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            for (i in calendarRecordViewModel.exercisesOnDate.indices) {
                                Text(
                                    text =
                                    "${calendarRecordViewModel.exercisesOnDate.get(i).exerciseIntensity.exerciseIntensity} 운동을 ${
                                        calendarRecordViewModel.exercisesOnDate.get(i).exerciseAccTime
                                    } 지속했음",
                                    textAlign = TextAlign.Center,
                                    fontFamily = gmarketsansLight,
                                    fontSize = 18.sp,
                                    color = Gray900,
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun isValidRecord(calendarRecordViewModel: CalendarRecordViewModel): Boolean {

    if (calendarRecordViewModel.breakfastFoodType.foodType != "음식 기록 안함")
        return true
    if (calendarRecordViewModel.lunchFoodType.foodType != "음식 기록 안함")
        return true
    if (calendarRecordViewModel.dinnerFoodType.foodType != "음식 기록 안함")
        return true
    if (calendarRecordViewModel.sleepAt != "-1")
        return true
    if (calendarRecordViewModel.awakeAt != "-1")
        return true
    if (calendarRecordViewModel.exerciseTime != "00시간 00분")
        return true
    return false
}

@Composable
private fun Day(day: CalendarDay, isSelected: Boolean, onClick: (CalendarDay) -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f) // This is important for square sizing!
            .testTag("MonthDay")
            .padding(6.dp)
            .clip(CircleShape)
            .background(color = if (isSelected) Navy200 else Color.Transparent)
            // Disable clicks on inDates/outDates
            .clickable(
                enabled = day.position == DayPosition.MonthDate,
                //showRipple = !isSelected,
                onClick = { onClick(day) },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = if (day.position == DayPosition.MonthDate) {
                if (day.date.dayOfWeek == DayOfWeek.SUNDAY || day.date.dayOfWeek == DayOfWeek.SATURDAY) {
                    Color.Red // 주말인 경우 빨간색
                } else {
                    Color.Black // 주말이 아닌 경우 검은색
                }
            } else {
                Color.White
            }

        )
    }
}

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN),
                color = Gray900
            )
        }
    }
}

@Composable
fun SimpleCalendarTitle(
    modifier: Modifier,
    currentMonth: YearMonth,
    goToPrevious: () -> Unit,
    goToNext: () -> Unit,
) {
    Row(
        modifier = modifier.height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CalendarNavigationIcon(
            icon = painterResource(id = com.gunpang.common.R.drawable.ic_chevron_left),
            contentDescription = "Previous",
            onClick = goToPrevious,
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .testTag("MonthTitle"),
            text = currentMonth.displayText(),
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            color = Gray900,
            fontWeight = FontWeight.Medium,
            fontFamily = gmarketsansBold,
        )
        CalendarNavigationIcon(

            icon = painterResource(id = com.gunpang.common.R.drawable.ic_chevron_right),
            contentDescription = "Next",
            onClick = goToNext,
        )
    }
}

@Composable
private fun CalendarNavigationIcon(
    icon: Painter,
    contentDescription: String,
    onClick: () -> Unit,
    //이렇게 넣으면 한번에 정의 가넝
    tint: Color = Gray600
) = Box(
    modifier = Modifier
        .fillMaxHeight()
        .aspectRatio(1f)
        .clip(shape = CircleShape)
        .clickable(role = Role.Button, onClick = onClick),
) {
    Icon(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .align(Alignment.Center),
        tint = tint,
        painter = icon,
        contentDescription = contentDescription,
    )
}

fun YearMonth.displayText(short: Boolean = false): String {
    // return "${this.month.displayText(short = short)} ${this.year}"
    return "${this.month.displayText(short = short)}"
}

fun Month.displayText(short: Boolean = true): String {
    val style = if (short) TextStyle.SHORT else TextStyle.FULL
    return getDisplayName(style, Locale.KOREAN)
}

@Composable
fun rememberFirstMostVisibleMonth(
    state: CalendarState,
    viewportPercent: Float = 50f,
): CalendarMonth {
    val visibleMonth = remember(state) { mutableStateOf(state.firstVisibleMonth) }
    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo.firstMostVisibleMonth(viewportPercent) }
            .filterNotNull()
            .collect { month -> visibleMonth.value = month }
    }
    return visibleMonth.value
}

private fun CalendarLayoutInfo.firstMostVisibleMonth(viewportPercent: Float = 50f): CalendarMonth? {
    return if (visibleMonthsInfo.isEmpty()) {
        null
    } else {
        val viewportSize = (viewportEndOffset + viewportStartOffset) * viewportPercent / 100f
        visibleMonthsInfo.firstOrNull { itemInfo ->
            if (itemInfo.offset < 0) {
                itemInfo.offset + itemInfo.size >= viewportSize
            } else {
                itemInfo.size - itemInfo.offset >= viewportSize
            }
        }?.month
    }
}