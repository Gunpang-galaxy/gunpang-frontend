package com.gunpang.watch_ui.exercise.onExercise

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.ui.app.watch.exercise.ExerciseMenuScreen
import com.gunpang.watch_ui.theme.Gray600
import com.gunpang.watch_ui.theme.Navy500
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.SpringIndicatorType
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnExercise(mainPagerState: PagerState,
               coroutineScope: CoroutineScope,
               navController: NavHostController
) {
    // TODO:  ExerciseMenuScreen, CurrentStatusScreen 으로 이동하기
    val pageCount by remember { mutableIntStateOf(2) }
    val pagerState = rememberPagerState(initialPage = 0)

    GunpangScreenWrapper {
        Column(
            modifier = Modifier.fillMaxSize(),
            ) {
            VerticalPager(
                modifier = Modifier.weight(0.9f),
                state = pagerState,
                pageCount = pageCount
            ) { page ->
                when (page) {
                    0 -> CurrentStatusScreen()
                    1 -> ExerciseMenuScreen(navController)
                }
            }
            /*
            DotsIndicator(
                modifier = Modifier
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
            )*/
        }
    }
}