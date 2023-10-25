package com.gunpang.ui.app.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gunpang.ui.theme.Gray600
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.gmarketsans

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun topBar(){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Gray900,
        ),
        title = {
            Text(
                "건팡!",
                fontFamily = gmarketsans,
                maxLines = 1,
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description",
                    tint = Gray600
                )
            }
        },
    )
}