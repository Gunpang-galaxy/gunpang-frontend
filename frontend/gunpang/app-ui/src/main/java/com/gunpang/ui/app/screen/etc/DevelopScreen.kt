package com.gunpang.ui.app.screen.etc

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gunpang.ui.app.common.ContentsNoRecord
import com.gunpang.ui.app.common.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevelopScreen(
    navController: NavController,
) {
    Scaffold(
        topBar={
            TopBar(
                navController = navController,
                title="",
                hasUndo = true
            ) },
    ) {
            it -> Surface(
        modifier = Modifier
            .padding(it)
    ) {
        ContentsNoRecord(
            reason = "추후 개발"
        )
    }
    }
}
