package com.gunpang.ui.app.watch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunpang.common.R
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.watch_ui.theme.Gray300
import com.gunpang.watch_ui.theme.Gray600
import com.gunpang.watch_ui.theme.Green400
import com.gunpang.watch_ui.theme.Green500
import com.gunpang.watch_ui.theme.Navy600


@Preview(name = "워치 메인 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun WatchMain() {
    var progress by remember { mutableStateOf(0.5f) } // Initial progress value (0.5 = 50%)

    GunpangScreenWrapper {

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
            painter = painterResource(id = R.drawable.baccus), // 이미지 리소스 변경 필요
            contentDescription = null,
            modifier = Modifier.size(96.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        /*목록버튼*/
        PageIndicator(totalPages = 3, currentPage = 1)
    }
}




@Composable
private fun PageIndicator(totalPages: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        repeat(totalPages) { page ->
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .background(
                        if (page == currentPage) Navy600 else Gray600,
                        CircleShape
                    )
                    .padding(end = 8.dp)
            )
        }
    }
}