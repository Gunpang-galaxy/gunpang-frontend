package com.gunpang.ui.app.screen.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.ui.theme.Gray900

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    showSystemUi = true)
fun MainContent(modifier: Modifier = Modifier
    .fillMaxWidth()
    .fillMaxHeight()){
    BoxWithConstraints(
        modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        var status : AvatarStatusCode = AvatarStatusCode.GRADUATED
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 2f)
                .padding(top = 12.dp, bottom = 20.dp),
                contentAlignment = Alignment.Center
            ){
                AvatarGoal(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                    avatarStatus= status,
                    goalList = listOf<String>(
                        "월, 수 운동하기",
                        "23:30 - 06:00",
                        "식사 기록하기"
                    ))
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 4f, fill = true),
                contentAlignment = Alignment.Center
            ){
                AvatarInfo(avatarStatus = status)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 4f, fill = true),
                contentAlignment = Alignment.Center
            ){


                when{
                    status == AvatarStatusCode.ALIVE ->
                        AvatarTodayInfo()
                    status == AvatarStatusCode.DEAD ->
                        FinishedAvatarInfo(statusCode = status)

                    else -> // status == AvatarStatusCode.GRADUDATE
                        FinishedAvatarInfo(statusCode = status)

                }
            }
        }
    }


}