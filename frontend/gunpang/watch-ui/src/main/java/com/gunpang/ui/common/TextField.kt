package com.gunpang.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gunpang.ui.theme.Gray900
import com.gunpang.ui.theme.Shapes
import com.gunpang.ui.theme.galmuriTyop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(
    defaultValue: String = "",
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Number,
    width: Int = 150,
    leftPadding: Int = 0,
    rightPadding: Int = 0,
    topPadding: Int = 0,
) {
    var value by remember { mutableStateOf(defaultValue) }

    TextField(
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        modifier = Modifier
            .background(color = Color.Transparent)
            .width(width.dp)
            .padding(start = leftPadding.dp, end = rightPadding.dp, top = topPadding.dp)
            .border(color = Color.White, width = 1.dp, shape = Shapes.large),
        singleLine = true, // enter 입력 불가
        textStyle = LocalTextStyle.current.copy(
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = galmuriTyop.title2.fontFamily
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}