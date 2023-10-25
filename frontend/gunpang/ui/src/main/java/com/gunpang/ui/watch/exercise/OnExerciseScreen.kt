package com.gunpang.ui.watch.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.ui.watch.common.WatchDivider
import androidx.wear.compose.material.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.gunpang.common.R

@Preview(name = "운동중 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun OnExerciseScreen() {
    GunpangScreenWrapper {
        Image(
            painter = painterResource(id = R.drawable.baccus), // 여기서 'your_icon'을 실제 리소스 이름으로 바꾸어야 합니다.
            contentDescription = "아령이미지",
            modifier = Modifier.size(36.dp)
        )
        TimeShow(minute="33",second="10")
        WatchDivider(width=100.dp)
        BpmShow(bpm="180")
        Image(
            painter = painterResource(id = R.drawable.baccus), // 여기서 'your_icon'을 실제 리소스 이름으로 바꾸어야 합니다.
            contentDescription = "하트이미지",
            modifier = Modifier.size(36.dp)
        )
    }
}
@Composable
fun TimeShow(minute:String="10",second:String="10"){
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = minute, fontSize = 25.sp)
        Text(text = "m",Modifier.paddingFromBaseline(bottom=6.dp))
        Text(text = second, fontSize = 25.sp)
        Text(text = "s",Modifier.paddingFromBaseline(bottom=6.dp))
    }
}
@Composable
fun BpmShow(bpm:String="128"){
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = bpm, fontSize = 25.sp)
        Text(text = "bpm",Modifier.paddingFromBaseline(bottom=6.dp))
    }
}