package com.gunpang.watch_ui.food.selectFood

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberScalingLazyListState
import com.gunpang.common.R
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.domain.watch.WatchFeedViewModel
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.watch_ui.common.WatchButton
import com.gunpang.watch_ui.common.WatchDivider
import com.gunpang.watch_ui.theme.Gray800
import com.gunpang.watch_ui.theme.Gray900
import com.gunpang.watch_ui.theme.Pink200
import com.gunpang.watch_ui.theme.galmuri
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectFoodScreen(
    mainPagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController
) {

    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)
    val watchFeedViewModel = viewModel<WatchFeedViewModel>(viewModelStoreOwner)

    GunpangScreenWrapper {
        val listState = rememberScalingLazyListState()
        Image(
            painter = painterResource(id = R.drawable.food_onigiri), // 여기서 'your_icon'을 실제 리소스 이름으로 바꾸어야 합니다.
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = "오늘 먹은 밥",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = galmuri
        )
        WatchDivider()

        val items = listOf("건강하게", "적당하게", "불량하게")
        var selectedIndex by remember { mutableStateOf(-1) }
        ScalingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            autoCentering = AutoCenteringParams(itemIndex = 0),
            state = listState
        ) {
            items(items.size){index->
                val isSelected = index==selectedIndex;
                val backgroundColor = if (isSelected) Pink200 else Gray900
                val contentColor = if (isSelected) Gray800 else Color.White

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    WatchButton(items[index], color= backgroundColor, textColor=contentColor) {
                    }
                }
            }
            item {
                // 건강하게
                WatchButton("기록") {
                    //TODO:기록
                    //watchFeedViewModel.feedFood()

                    navController.navigate(WatchNavItem.Main.route)
                }
            }

        }
    }


}

