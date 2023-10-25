package com.gunpang.ui.app.watch.history

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.ui.app.watch.common.WatchButton
@Preview(name = "히스토리 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun HistoryScreen() {
    GunpangScreenWrapper {
        /*수면시간*/
        /*운동시간*/
        /*먹은밥*/
        WatchButton(text = "뒤로가기") {
        }
    }
}