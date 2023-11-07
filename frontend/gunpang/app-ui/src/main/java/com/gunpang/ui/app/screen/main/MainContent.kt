package com.gunpang.ui.app.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.domain.app.avatar.AvatarViewModel
import com.gunpang.domain.entity.AppAvatarAliveContent
import com.gunpang.ui.app.screen.main.composable.AvatarGoal
import com.gunpang.ui.app.screen.main.composable.AvatarInfo
import com.gunpang.ui.app.screen.main.composable.AvatarTodayInfo
import com.gunpang.ui.app.screen.main.composable.FinishedAvatarInfo

@Composable
fun MainContent(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    avatarViewModel: AvatarViewModel
    ){
    Box(
        modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        var status : AvatarStatusCode = avatarViewModel.appAvatar.status
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 2.5f)
                .padding(top = 12.dp, bottom = 20.dp),
                contentAlignment = Alignment.Center
            ){
                AvatarGoal(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    avatarStatus= status,
                    goal = avatarViewModel.avatarGoal
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 4f, fill = true),
                contentAlignment = Alignment.Center
            ){
                AvatarInfo(avatarViewModel = avatarViewModel)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 4f, fill = true),
                contentAlignment = Alignment.Center
            ){
                when{
                    status == AvatarStatusCode.ALIVE ->
                        AvatarTodayInfo(contents = avatarViewModel.avatarAliveContents)
                    status == AvatarStatusCode.DEAD ->
                        FinishedAvatarInfo(
                            statusCode = status,
                            startedDate = avatarViewModel.appAvatar.startedDate,
                            finishedDate = avatarViewModel.appAvatar.finishedDate!!,
                            contents = avatarViewModel.avatarDeadContents)
                    else -> // status == AvatarStatusCode.GRADUDATE
                        FinishedAvatarInfo(
                            statusCode = status,
                            startedDate = avatarViewModel.appAvatar.startedDate,
                            finishedDate = avatarViewModel.appAvatar.finishedDate!!,
                            contents = avatarViewModel.avatarGraduatedContents)
                }
            }
        }
    }


}