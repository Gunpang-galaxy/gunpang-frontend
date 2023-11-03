package com.gunpang.ui.app.screen.main.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.common.R
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.domain.app.avatar.AvatarViewModel
import com.gunpang.domain.entity.AppAvatar
import com.gunpang.ui.theme.Gray300
import com.gunpang.ui.theme.Gray500
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Green500
import com.gunpang.ui.theme.Shapes
import com.gunpang.ui.theme.gmarketsansBold



@Composable
fun AvatarInfo(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    avatarViewModel: AvatarViewModel
    ){
    Row(modifier = Modifier
        .fillMaxSize(),
        ){
        Box(
            modifier = Modifier
                .weight(weight = 3f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ){
            if(avatarViewModel.prevId != -1){
                IconButton(onClick = {
                    avatarViewModel.getAvatar(avatarViewModel.prevId)
                } ){
                    Icon(
                        painter= painterResource(id = R.drawable.ic_prev_arrow),
                        contentDescription = "다음 아바타 보기",
                        tint = Gray500
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(weight = 4f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ){
            AvatarContent(avatar= avatarViewModel.appAvatar)
        }
        Box(
            modifier = Modifier
                .weight(weight = 3f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ){
            if(avatarViewModel.nextId != -1){
                IconButton(onClick = {
                    avatarViewModel.getAvatar(avatarViewModel.nextId)
                } ){
                    Icon(
                        painter= painterResource(id = R.drawable.ic_next_arrow),
                        contentDescription = "다음 아바타 보기",
                        tint = Gray500
                    )
                }
            }
        }
    }
}

@Composable
fun AvatarContent(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    avatar: AppAvatar
){
    Column(modifier = Modifier.fillMaxSize()){
        Spacer(modifier = Modifier.weight(1f))

        AvatarName(modifier = Modifier
            .fillMaxWidth()
            .weight(1.5f),
            name = avatar.avatarName
        )
        Box( // 아바타 이미지
            modifier = Modifier
                .weight(weight = 6f)
                .fillMaxWidth(),
        ){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = avatar.avatarType.imageId),
                contentDescription = avatar.avatarName,
                contentScale = ContentScale.Fit
            )
        }
        Box(modifier = Modifier.weight(1.5f)){
            when {
                avatar.status == AvatarStatusCode.ALIVE ->
                    AliveAvatarStatus(
                        modifier = Modifier.fillMaxSize(),
                        level = avatar.stage.level,
                        hp = avatar.healthPoint)
                else ->
                    FinishedAvatarStatus(
                        modifier = Modifier.fillMaxSize(),
                        avatarStatus = avatar.status)
            }

        }
        //Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun FinishedAvatarStatus(modifier: Modifier, avatarStatus: AvatarStatusCode) {
    Box(modifier = modifier,
        contentAlignment = Alignment.Center){
        Text(
            text = avatarStatus.status,
            fontFamily = gmarketsansBold,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Gray900
        )
    }

}

@Composable
fun AliveAvatarStatus(
    modifier: Modifier = Modifier.fillMaxSize(),
    level: Int,
    hp : Float,
    ) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically){
        Text(
            modifier = Modifier.weight(1f),
            text = "Lv.$level",
            fontFamily = gmarketsansBold,
            textAlign = TextAlign.Left,
            fontSize = 20.sp,
            color = Gray900
        )
        LinearProgressIndicator(
            modifier= Modifier
                .height(15.dp)
                .padding(start = 4.dp)
                .weight(2f)
                .clip(Shapes.extraLarge),
            progress = hp,
            color = Green500,
            trackColor = Gray300,
        )
    }
}