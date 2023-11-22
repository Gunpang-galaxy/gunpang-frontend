package com.gunpang.ui.app.screen.bodyComposition

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gunpang.data.manager.AppHealthConnectManager
import com.gunpang.common.R
import com.gunpang.domain.app.bodyComposition.BodyCompositionViewModel
import com.gunpang.domain.app.healthconnect.AppHealthViewModel
import com.gunpang.ui.app.common.BottomNavBar
import com.gunpang.ui.app.common.CommonButton
import com.gunpang.ui.app.common.TopBar
import com.gunpang.ui.app.screen.goal.exerciseAvailable
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.galmuriTyop
import com.gunpang.ui.theme.gmarketsans
import com.gunpang.ui.theme.gmarketsansBold
import com.gunpang.ui.theme.gmarketsansTypo

@Composable
fun BodyCompositionRegistration (
    navController: NavController,
    appHealthViewModel: AppHealthViewModel
) {
    Column(
        modifier = Modifier
            .height(650.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "체성분을\n측정해주세요",
            style = galmuriTyop.titleLarge,
            color = Gray900,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 50.dp)
        )
        Spacer(
            modifier = Modifier.height(50.dp)
        )
        CommonButton(
            text = "데이터 가져오기",
            onClick = {
                appHealthViewModel.initBodyComposition()
                navController.navigate("mainScreen")
            }
        )
    }
}
