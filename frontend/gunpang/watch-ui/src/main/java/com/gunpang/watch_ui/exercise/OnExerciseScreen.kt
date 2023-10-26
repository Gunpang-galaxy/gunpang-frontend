package com.gunpang.watch_ui.exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunpang.ui.app.watch.common.GunpangScreenWrapper
import com.gunpang.watch_ui.common.WatchDivider
import androidx.wear.compose.material.Text
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.gunpang.common.R

@Preview(name = "운동중 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun OnExerciseScreen() {
    GunpangScreenWrapper {
        Column(modifier = Modifier.fillMaxSize() ,horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.dumbell),
                contentDescription = "아령이미지",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            TimeShow(minute="33",second="10")
            WatchDivider(fraction=0.8f, thickness = 5.dp)
            BpmShow(bpm="180")
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "하트이미지",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}
@Composable
fun TimeShow(minute:String="10",second:String="10"){
    Row(
        modifier = Modifier.padding(bottom = 4.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = minute, fontSize = 25.sp, modifier = Modifier.padding(1.dp))
        Text(text = "m",Modifier.paddingFromBaseline(bottom=3.dp).padding(1.dp))
        Text(text = second, fontSize = 25.sp, modifier = Modifier.padding(1.dp))
        Text(text = "s",Modifier.paddingFromBaseline(bottom=3.dp).padding(1.dp))
    }
}
@Composable
fun BpmShow(bpm:String="128"){
    Row(
        modifier = Modifier.padding(top= 2.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = bpm, fontSize = 25.sp)
        Text(text = "bpm",Modifier.paddingFromBaseline(bottom=3.dp).padding(1.dp))
    }
}