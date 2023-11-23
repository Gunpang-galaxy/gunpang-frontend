package com.gunpang.ui.exercise.onExercise

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gunpang.ui.common.GunpangScreenWrapper
import com.gunpang.ui.common.WatchDivider
import androidx.wear.compose.material.Text
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.google.android.horologist.health.composables.ActiveDurationText
import com.gunpang.common.R
import com.gunpang.data.service.ExerciseScreenState

import com.gunpang.ui.exercise.formatElapsedTime
@Composable
fun CurrentStatusScreen(uiState: ExerciseScreenState) {
    val lastActiveDurationCheckpoint = uiState.exerciseState?.activeDurationCheckpoint
    val exerciseState = uiState.exerciseState?.exerciseState

    GunpangScreenWrapper {
        Column(modifier = Modifier.fillMaxSize() ,horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.timer),
                contentDescription = "타이머이미지",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            //TimeShow(minute="33",second="10")
            if (exerciseState != null && lastActiveDurationCheckpoint != null) {
                Log.d("STATE","exerciseState"+exerciseState+"lastActiveDuration"+lastActiveDurationCheckpoint)

                ActiveDurationText(
                        checkpoint = lastActiveDurationCheckpoint,
                        state = uiState.exerciseState!!.exerciseState!!,
                    ) {
                        Text(text = formatElapsedTime(it, includeSeconds = true),fontSize = 25.sp)
                    }
            } else {
                Text(text = "--분--초")
            }

            WatchDivider(fraction=0.9f)
            BpmShow(bpm=""+String.format("%3.0f", uiState.exerciseState?.exerciseMetrics?.heartRate))
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
        modifier = Modifier.padding(bottom = 3.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = minute, fontSize = 25.sp, modifier = Modifier.padding(1.dp))
        Text(text = "m",Modifier.paddingFromBaseline(bottom=2.dp).padding(1.dp))
        Text(text = second, fontSize = 25.sp, modifier = Modifier.padding(1.dp))
        Text(text = "s",Modifier.paddingFromBaseline(bottom=2.dp).padding(1.dp))
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