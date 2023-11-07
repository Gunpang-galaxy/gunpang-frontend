package com.gunpang.ui.app.screen.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gunpang.common.R
import com.gunpang.common.navigation.AppNavItem
import com.gunpang.domain.app.landing.LandingViewModel
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.galmuriTyop
import com.gunpang.ui.theme.gmarketsansTypo

@Composable
fun LandingExceptionHandler(
    errorMessage: String,
    explanation: String,
    pageNavigateText: String,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.gunpang_exception),
            contentDescription = "건팡 예외 발생",
            modifier = Modifier
                .size(200.dp)
        )
        Text(
            text = errorMessage,
            style = gmarketsansTypo.displaySmall,
            color = Gray900,
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
        )
        Text(
            text = explanation,
            style = gmarketsansTypo.titleMedium,
            color = Gray900
        )
        Spacer(
            modifier = Modifier.size(70.dp)
        )
        CommonButton(
            text = pageNavigateText,
            onClick = {
                onClick()
            }
        )
    }
}


@Composable
fun LoginFailException(
    navController: NavController
) {
    LandingExceptionHandler(
        errorMessage = "아직 로그인 하지 않았어요",
        explanation = "로그인 화면에서\n로그인을 완료해 주세요",
        pageNavigateText = "돌아가기",
        onClick = {

        }
    )
}

@Composable
fun WatchNotConnectedException(
    navController: NavController
) {
    LandingExceptionHandler(
        errorMessage = "갤럭시워치와\n연동할 수 없어요",
        explanation = "핸드폰과 갤럭시워치가 연결되어있는지 확인해주세요",
        pageNavigateText = "연결하기",
        onClick = {

        }
    )
}

@Composable
fun WatchAppNotInstalledException(
    navController: NavController,
    landingViewModel: LandingViewModel
) {
    LandingExceptionHandler(
        errorMessage = "갤럭시워치에\n건팡을 설치해주세요",
        explanation = "갤럭시워치에 건팡이\n설치되어 있는지 확인해주세요",
        pageNavigateText = "워치에 앱 설치하기",
        onClick = {
            landingViewModel.requestWearableAppInstall()
        }
    )
}

@Composable
fun AvatarNotCreatedException(
    navController: NavController
) {
    LandingExceptionHandler(
        errorMessage = "아바타가 없어요",
        explanation = "아바타를 생성해주세요",
        pageNavigateText = "아바타 생성하기",
        onClick = {
            navController.navigate(AppNavItem.AvatarEgg.routeName)
        }
    )
}

@Composable
fun GoalNotCreatedException(
    navController: NavController
) {
    LandingExceptionHandler(
        errorMessage = "목표가 없어요",
        explanation = "목표를 생성해주세요",
        pageNavigateText = "목표 생성하기",
        onClick = {

        }
    )
}

