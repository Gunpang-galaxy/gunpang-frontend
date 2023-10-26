package com.gunpang.ui.app.watch.exercise

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.watch_ui.common.WatchButton

@Preview(name = "운동 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun ExerciseScreen(){
    GunpangScreenWrapper {
        WatchButton(text = "시작하기") {
            
        }
        WatchButton(text = "일시정지") {

        }
        WatchButton(text = "그만하기") {

        }
    }

}