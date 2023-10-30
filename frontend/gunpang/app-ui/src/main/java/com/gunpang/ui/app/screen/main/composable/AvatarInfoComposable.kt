package com.gunpang.ui.app.screen.main.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gunpang.common.R
import com.gunpang.common.code.AvatarStatusCode
import com.gunpang.ui.theme.Gray300
import com.gunpang.ui.theme.Gray50
import com.gunpang.ui.theme.Gray500
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Green500
import com.gunpang.ui.theme.Shapes
import com.gunpang.ui.theme.gmarketsansBold


@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 390,
    heightDp = 390
)
@Composable
fun AvatarInfo(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    avatarStatus: AvatarStatusCode = AvatarStatusCode.ALIVE,
    avatarImage: Int = R.drawable.avatar_chick,
    prevId: Int = 1,
    nextId: Int = 1,
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
            if(prevId != -1){
                IconButton(onClick = { /*TODO*/ } ){
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
            AvatarContent(avatarStatus = avatarStatus)
        }
        Box(
            modifier = Modifier
                .weight(weight = 3f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ){
            if(nextId != -1){
                IconButton(onClick = { /*TODO*/ } ){
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
@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 156,
    heightDp = 390
    )
fun AvatarContent(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
    avatarStatus: AvatarStatusCode = AvatarStatusCode.ALIVE,){
    Column(modifier = Modifier.fillMaxSize()){
        Spacer(modifier = Modifier.weight(1f))

        AvatarName(modifier = Modifier
            .fillMaxWidth()
            .weight(1.5f)
        )
        Box(
            modifier = Modifier
                .weight(weight = 6f)
                .fillMaxWidth(),
        ){
            AvatarImage()
        }
        Box(modifier = Modifier.weight(1.5f)){
            when {
                avatarStatus == AvatarStatusCode.ALIVE -> AliveAvatarStatus(modifier = Modifier.fillMaxSize())
                else -> FinishedAvatarStatus(modifier = Modifier.fillMaxSize(), avatarStatus = avatarStatus)
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

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 156,
    heightDp = 40
)
@Composable
fun AliveAvatarStatus(
    modifier: Modifier = Modifier.fillMaxSize(),
    level: Int = 4,
    hp : Float = 0.3f,
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

@Preview()
@Composable
fun AvatarName(modifier: Modifier = Modifier.fillMaxWidth(), name: String = "삐약쓰"){
    Box(modifier = modifier,contentAlignment = Alignment.Center){
        Box(modifier = Modifier
            .width(100.dp)
            .height(40.dp)
            .clip(Shapes.medium)
            .background(color = Gray50),
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                text = name,
                fontFamily = gmarketsansBold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Gray900
            )
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
    widthDp = 156,
    heightDp = 156
)
@Composable()
fun AvatarImage(modifier: Modifier = Modifier.fillMaxSize(),
                imageId : Int = R.drawable.avatar_chick,
                contentDescription: String = "아바타 이미지"){
    Image(
        modifier = modifier,
        painter = painterResource(id = imageId),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit
    )
}