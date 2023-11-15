package com.gunpang.ui.avatar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.WatchAvatarViewModel
import com.gunpang.ui.common.BackgroundWrapper
import com.gunpang.ui.theme.Gray300
import com.gunpang.ui.theme.Green500
import kotlinx.coroutines.CoroutineScope

//@Preview(name = "아바타 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AvatarScreen(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController,
    watchAvatarViewModel: WatchAvatarViewModel // already init in MainScreen
){

    var progress by remember { mutableStateOf(watchAvatarViewModel.healthPoint) } // Initial progress value (0.5 = 50%)
    BackgroundWrapper(watchAvatarViewModel = watchAvatarViewModel) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
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
                painter = painterResource(id = watchAvatarViewModel.avatarTypeId.imageId), // 이미지 리소스 변경 필요
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp)
                    .clickable {
                        navController.navigate(WatchNavItem.TodayHistory.route)
                    }
            )
        }
    }
}