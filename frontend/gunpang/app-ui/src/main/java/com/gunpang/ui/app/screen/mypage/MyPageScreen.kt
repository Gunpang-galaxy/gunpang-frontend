package com.gunpang.ui.app.screen.mypage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gunpang.common.navigation.AppNavItem
import com.gunpang.data.repository.DataApplicationRepository
import com.gunpang.domain.app.AppViewModel
import com.gunpang.domain.app.landing.LandingViewModel
import com.gunpang.domain.app.landing.PersonalInfoViewModel
import com.gunpang.domain.app.user.UserViewModel
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.app.common.TopBar
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsans
import com.gunpang.ui.theme.gmarketsansBold

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyPageScreen(
    navController: NavController,
    userViewModel: UserViewModel,
    landingViewModel: LandingViewModel
){
    LaunchedEffect(key1 = true){
        userViewModel.init()
    }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = "내 정보",
                hasUndo = true,
                )
        },
        containerColor = Color.White
    ) {
        it ->
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(90.dp))
                val infoModifier = Modifier.fillMaxWidth().height(70.dp)
                infoText(
                    modifier = infoModifier,
                    fieldName = "이메일",
                    fieldValue = userViewModel.userInfo.email
                )
                infoText(
                    modifier = infoModifier,
                    fieldName = "성별",
                    fieldValue = userViewModel.userInfo.gender.kor
                )
                infoText(
                    modifier = infoModifier,
                    fieldName = "출생",
                    fieldValue = userViewModel.userInfo.birth.toString()
                )
                infoText(
                    modifier = infoModifier,
                    fieldName = "키",
                    fieldValue = userViewModel.userInfo.height.toString()
                )
                Spacer(modifier = Modifier.height(24.dp))
                CommonButton(
                    text = "워치 로그인",
                    onClick = {
                        landingViewModel.registerWearable()
                    },
                )
                Spacer(modifier = Modifier.height(24.dp))
                CommonButton(
                    text = "로그아웃",
                    onClick = {
                        Log.d("MyPageScreen", "버튼 클릭")
                        userViewModel.logout() // LogOut 로직 처리

                        Log.d("MyPageScreen", "LogOut 로직 처리 후")
                        DataApplicationRepository().removeValue("playerId")
                        navController.navigate(AppNavItem.Login.routeName) // 페이지 이동
                    },
                )
            }
        }
    }
}

@Composable
fun infoText (
    modifier: Modifier,
    fieldName: String,
    fieldValue: String
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = fieldName,
                fontFamily = gmarketsansBold,
                color = Gray900,
                fontSize = 20.sp,
            )
            Text(
                text = fieldValue,
                fontFamily = gmarketsans,
                color = Gray900,
                fontSize = 20.sp,
                textAlign = TextAlign.Right
            )
        }
    }

}
