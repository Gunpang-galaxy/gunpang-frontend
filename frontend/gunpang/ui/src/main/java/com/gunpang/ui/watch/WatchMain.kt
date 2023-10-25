package com.gunpang.ui.app.watch

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper

@Preview(name = "워치 메인 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun WatchMain() {
    GunpangScreenWrapper {

        /*ProgressBar*/
        Spacer(modifier = Modifier.height(6.dp))

        /*Avatar*/

        Spacer(modifier = Modifier.height(6.dp))

        /*목록버튼*/


    }
}
