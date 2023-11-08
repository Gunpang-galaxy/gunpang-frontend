package com.gunpang.ui.app.screen.main

import android.text.format.DateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.R
import com.gunpang.common.navigation.AppNavItem
import com.gunpang.domain.app.avatar.AvatarViewModel
import com.gunpang.domain.entity.AppAvatar
import com.gunpang.domain.entity.AppAvatarDeadContent
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.app.screen.main.composable.LivingDate
import com.gunpang.ui.app.screen.main.composable.AvatarName
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansBold
import java.time.LocalDate
import java.util.Date

@Composable
fun AvatarFinishedScreen(
    navController: NavController,
    avatarViewModel: AvatarViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(124.dp))
        // 성장 멈춘 이유
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
            contentAlignment = Alignment.Center
            ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = avatarViewModel.appAvatar.status.status
                ,
                fontFamily = gmarketsansBold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Gray900
                )
        }
        Spacer(modifier = Modifier.height(72.dp))
        // 아바타 이름
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
            contentAlignment = Alignment.Center){
            AvatarName(
                name = avatarViewModel.appAvatar.avatarName
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // 아바타 이미지
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(158.dp),

            ){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = when {
                    avatarViewModel.appAvatar.status == AvatarStatusCode.GRADUATED ->
                        avatarViewModel.appAvatar.avatarType.imageId
                    else -> R.drawable.tomb_stone_kor
                }),
                contentDescription = avatarViewModel.appAvatar.avatarName,
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        // 아바타 산날
        Box(){
            val finishedDate : String = if(avatarViewModel.appAvatar.finishedDate != null)
                avatarViewModel.appAvatar.finishedDate!! else
                DateFormat.format("yyyy.MM.dd", Date()).toString()
            LivingDate(
                startedDate = avatarViewModel.appAvatar.startedDate,
                finishedDate = finishedDate
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        // 죽은 이유
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = when {
                    avatarViewModel.appAvatar.status == AvatarStatusCode.GRADUATED -> "아바타 성장 성공"
                    else -> avatarViewModel.avatarDeadContents.deathCause.status

                },
                fontFamily = gmarketsansBold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                color = Gray900
            )
        }
        Spacer(modifier = Modifier.height(72.dp))
        // 다시 시작하기 버튼
        var text = when {
            avatarViewModel.appAvatar.status == AvatarStatusCode.GRADUATED -> "한번 더?"
            else -> "다시시작.."
        }
        CommonButton(
            text = text,
            onClick = {
                // 혹시 몰라서 avatarViewModel.appAvatar 초기화
                avatarViewModel.appAvatar = AppAvatar()
                // 아바타 다시 시작하기 위해 아바타 생성 페이지로 navigate
                navController.navigate(AppNavItem.AvatarEgg.routeName)
            }
        )

    }
}