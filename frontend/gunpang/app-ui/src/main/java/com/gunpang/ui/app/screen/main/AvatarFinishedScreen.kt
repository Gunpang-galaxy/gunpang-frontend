package com.gunpang.ui.app.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.common.code.DeathCauseCode
import com.gunpang.common.R
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.app.screen.main.composable.AvatarImage
import com.gunpang.ui.app.screen.main.composable.AvatarName
import com.gunpang.ui.app.screen.main.composable.LivingDate
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsansBold

@Composable
@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    widthDp = 390,
    heightDp = 850
)
fun AvatarFinishedScreen() {
    var avatarStatus = AvatarStatusCode.DEAD
    var avatarDeathCause = DeathCauseCode.EXERCISE_LACK
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(124.dp))
        // 성장 멈춘 이유
        Box(modifier = Modifier.fillMaxWidth().height(48.dp),
            contentAlignment = Alignment.Center
            ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = when {
                    avatarStatus == AvatarStatusCode.GRADUATED -> "졸업"
                    else -> "실패"
                },
                fontFamily = gmarketsansBold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Gray900
                )
        }
        Spacer(modifier = Modifier.height(72.dp))
        // 아바타 이름
        Box(modifier = Modifier.fillMaxWidth().height(40.dp),
            contentAlignment = Alignment.Center){
            AvatarName();
        }
        Spacer(modifier = Modifier.height(16.dp))

        // 아바타 이미지
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(158.dp),

            ){
            var imageId = when {
                avatarStatus == AvatarStatusCode.GRADUATED -> R.drawable.avatar_chick_graduated
                else -> R.drawable.tomb_stone_kor
            }
            AvatarImage(
                imageId = imageId,
                contentDescription = "죽은 이미지"
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        // 아바타 산날
        Box(){
            LivingDate()
        }
        Spacer(modifier = Modifier.height(48.dp))
        // 죽은 이유
        Box(modifier = Modifier.fillMaxWidth().height(48.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = when {
                    avatarStatus == AvatarStatusCode.GRADUATED -> "아바타 성장 성공"
                    else -> avatarDeathCause.status
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
            avatarStatus == AvatarStatusCode.GRADUATED -> "한번 더?"
            else -> "다시시작.."
        }
        CommonButton(text = text);

    }
}