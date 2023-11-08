package com.gunpang.ui.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.gunpang.common.R
import com.gunpang.common.code.InitCode
import com.gunpang.domain.watch.WatchLandingViewModel
import com.gunpang.ui.common.GunpangScreenWrapper
import com.gunpang.ui.common.WatchButton
import com.gunpang.ui.theme.Gray300
import com.gunpang.ui.theme.Green500
import com.gunpang.ui.theme.galmuri

@Composable
fun LandingScreen(watchLandingViewModel : WatchLandingViewModel) {
    LaunchedEffect(true){
        watchLandingViewModel.init()
    }
    when(watchLandingViewModel.initCode){
        InitCode.NOT_FOUND->{
            // 연결된 기기가 없습니다
            NotConnectedDevice()
        }

        InitCode.NOT_INSTALL->{
            NotInstalledApp(watchLandingViewModel)
        }

        InitCode.NOT_LOGIN ->{
            NotLogined(watchLandingViewModel)
        }

        InitCode.NOT_CONFIG ->{
            NotConfigued()
        }

        InitCode.FINISH->{

        }
        else->{
            Loading()
        }
    }


}
@Composable
fun NotConfigued(){
    GunpangScreenWrapper {
        Text(
            text = "내가 키울 캐릭터는?\n(터치해서 앱에서 뽑아보세요)",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = galmuri,
            fontSize = 20.sp,
        )
    }
}
@Composable
//@Preview(name = "로그인을 진행해주세요", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
fun NotLogined(watchLandingViewModel : WatchLandingViewModel){
    GunpangScreenWrapper {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "로그인을 진행해주세요",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = galmuri
            )

            Spacer(modifier = Modifier.height(6.dp))
            WatchButton(text = "로그인") {
                watchLandingViewModel.login()
            }

        }
    }
}
@Composable
//@Preview(name = "앱을 설치해주세요", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
fun NotInstalledApp(watchLandingViewModel : WatchLandingViewModel){
    GunpangScreenWrapper {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "앱을 설치해주세요",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = galmuri
            )

            Spacer(modifier = Modifier.height(6.dp))

            WatchButton(text = "휴대폰에 앱 설치") {
                watchLandingViewModel.openAppInStoreOnPhone()
            }
        }
    }
}
@Composable
@Preview(name = "연결된 기기가 없습니다.", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
fun NotConnectedDevice(){
    GunpangScreenWrapper {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "휴대폰과 연결이 뚜.. 뚜..",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = galmuri
            )

            Spacer(modifier = Modifier.height(6.dp))
            /*ProgressBar*/
            Image(
                painter = painterResource(id = R.drawable.gunpang_crushed),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Composable
@Preview(name = "로딩 화면", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
fun Loading(){
    GunpangScreenWrapper {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "로딩 중...",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = galmuri
            )

            Spacer(modifier = Modifier.height(6.dp))
            /*ProgressBar*/
            LinearProgressIndicator(
                progress = 0.3f,
                color = Green500,
                trackColor = Gray300,
                modifier = Modifier
                    .padding(8.dp)
                    .height(12.dp)
                    .width(107.dp)
                    .clip(RoundedCornerShape(size = 100.dp))
            )
        }
    }
}