package com.gunpang.ui.app.watch

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunpang.common.R
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.ui.app.watch.exercise.ExerciseScreen
import com.gunpang.ui.app.watch.food.FoodScreen
import com.gunpang.watch_ui.theme.Gray300
import com.gunpang.watch_ui.theme.Gray600
import com.gunpang.watch_ui.theme.Green500
import com.gunpang.watch_ui.theme.Navy500
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.SpringIndicatorType


@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "워치 메인 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun WatchMain() {
    val pageCount by remember { mutableIntStateOf(3) }
    val pagerState = rememberPagerState(initialPage =  1)
    GunpangScreenWrapper {

        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
            pageCount = pageCount
        ) { page ->
            when (page) {
                0 -> ExerciseScreen()
                1 -> AvatarScreen()
                2 -> FoodScreen()
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        DotsIndicator(
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


    }
}

@Composable
private fun AvatarScreen(){
    var progress by remember { mutableStateOf(0.5f) } // Initial progress value (0.5 = 50%)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /*ProgressBar*/
        LinearProgressIndicator(
            progress = progress,
            color = Green500,
            trackColor = Gray300,
            modifier = Modifier
                .padding(8.dp)
                .height(12.dp)
                .width(107.dp)
                .clip(RoundedCornerShape(size = 100.dp))
        )
        Spacer(modifier = Modifier.height(6.dp))

        /*Avatar*/
        Image(
            painter = painterResource(id = R.drawable.avatar_chick), // 이미지 리소스 변경 필요
            contentDescription = null,
            modifier = Modifier.size(96.dp)
        )
    }
}