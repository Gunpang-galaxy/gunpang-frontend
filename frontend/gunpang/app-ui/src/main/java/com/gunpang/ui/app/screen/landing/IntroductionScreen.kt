package com.gunpang.ui.app.screen.landing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.theme.galmuriTyop
import com.gunpang.ui.theme.Navy500
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.SpringIndicatorType

@Composable
fun IntroductionStructure(
    title: String,
    description: String,
) {
    Column(
        modifier = Modifier
            .height(650.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = galmuriTyop.titleLarge,
            modifier = Modifier.padding(bottom = 50.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = description,
            style = galmuriTyop.bodyMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun GoalIntroduction() {
    val title = "목표를 설정해보세요"
    val desc = "운동, 수명, 식습관에 대한\n목표를 설정할 수 있어요"
    IntroductionStructure(title, desc)
}

@Composable
fun ChangeIntroduction() {
    val title = "변화를 확인해보세요"
    val desc = "한 달뒤 변화를 확인해보세요"
    IntroductionStructure(title, desc)
}

@Composable
fun HabitIntroduction() {
    val title = "좋은 습관을\n유지시키도록\n도와줄께요"
    val desc = "건팡이가 습관을 키워줄거에요"
    IntroductionStructure(title, desc)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Introduction() {
    Column(
        modifier = Modifier.height(900.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val pageCount by remember { mutableIntStateOf(3) }
        val pagerState = rememberPagerState { pageCount }

        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState
        ) { page ->
            when (page) {
                0 -> GoalIntroduction()
                1 -> ChangeIntroduction()
                2 -> HabitIntroduction()
            }
        }
        CommonButton(
            text = "시작하기",
            alpha = if (pagerState.currentPage == pageCount - 1) 1f else 0f,
        )
        Spacer( // 시작하기 버튼과 dot indicator 사이의 간격
            modifier = Modifier.height(50.dp)
        )
        DotsIndicator(
            dotCount = pageCount,
            type = SpringIndicatorType(
                dotsGraphic = DotGraphic(
                    16.dp,
                    borderWidth = 2.dp,
                    borderColor = Navy500,
                    color = Color.Transparent
                ),
                selectorDotGraphic = DotGraphic(
                    14.dp,
                    color = Navy500
                )
            ),
            pagerState = pagerState
        )
    }
}