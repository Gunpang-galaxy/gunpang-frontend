package com.gunpang.ui.food

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Text
import com.gunpang.common.R.drawable.*
import com.gunpang.common.navigation.WatchNavItem
import com.gunpang.ui.common.WatchButton
import com.gunpang.ui.theme.galmuri
import kotlinx.coroutines.CoroutineScope

//@Preview(name = "음식 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodScreen(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    navController: NavHostController
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = baccus),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "나 배고파 ㅠ",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = galmuri
        )

        Spacer(modifier = Modifier.height(6.dp))

        WatchButton(text = "밥먹이기") {
            navController.navigate(WatchNavItem.SelectFood.route)
        }
    }

}