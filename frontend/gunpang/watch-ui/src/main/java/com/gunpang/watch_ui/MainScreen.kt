package com.gunpang.watch_ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gunpang.domain.watch.WatchAvatarViewModel
import com.gunpang.domain.watch.exercise.ExerciseViewModel
import com.gunpang.watch_ui.common.GunpangScreenWrapper
import com.gunpang.watch_ui.exercise.ExerciseScreen
import com.gunpang.watch_ui.food.FoodScreen
import com.gunpang.watch_ui.avatar.AvatarScreen
import com.gunpang.watch_ui.landing.Loading
import com.gunpang.watch_ui.theme.Gray600
import com.gunpang.watch_ui.theme.Navy500
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.SpringIndicatorType
import kotlinx.coroutines.CoroutineScope

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    mainPagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    watchAvatarViewModel: WatchAvatarViewModel,
    exerciseViewModel: ExerciseViewModel
) {

    LaunchedEffect(true){
        // TODO : DB연결시 주석 해제
        // watchAvatarViewModel.init()
    }
    GunpangScreenWrapper {
        if(isAvatarInfoConfigured())
            MainSwipe( mainPagerState, coroutineScope , navController ,watchAvatarViewModel, exerciseViewModel)
        else
            Loading()
    }
}

fun isAvatarInfoConfigured(): Boolean {
    //TODO: 아바타 어떤 정보가 필요한지
    return true
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainSwipe(
    mainPagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    watchAvatarViewModel: WatchAvatarViewModel,
    exerciseViewModel: ExerciseViewModel
){
    val pageCount by remember { mutableIntStateOf(3) }
    val pagerState = rememberPagerState(initialPage = 1,pageCount={pageCount})
    Column(
        //modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


            HorizontalPager(
                modifier = Modifier
                .weight(0.9f),
                state = pagerState,
            ) { page ->
                when (page) {
                    0 -> ExerciseScreen(mainPagerState, coroutineScope, navController)
                    1 -> AvatarScreen(mainPagerState, coroutineScope, navController,watchAvatarViewModel)
                    2 -> FoodScreen(mainPagerState, coroutineScope, navController)
                }
            }


        DotsIndicator(
            modifier = Modifier
                .weight(0.1f)
                .align(Alignment.CenterHorizontally) // 중앙 정렬
                .padding(6.dp), // 상하좌우에 패딩 적용
            dotCount = pageCount,
            type = SpringIndicatorType(
                dotsGraphic = DotGraphic(
                    8.dp,
                    borderWidth = 2.dp,
                    borderColor = Gray600,
                    color = Color.Transparent
                ),
                selectorDotGraphic = DotGraphic(
                    8.dp,
                    color = Navy500
                )
            ),
            pagerState = pagerState
        )
        /*


        */
    }
}