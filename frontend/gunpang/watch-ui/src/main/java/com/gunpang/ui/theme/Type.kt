package com.gunpang.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Typography
import com.gunpang.common.R

// fontfamily (수정 필요할 듯)

val galmuri = FontFamily(Font(R.font.galmuri11, FontWeight.Medium, FontStyle.Normal))
val galmuriBold = FontFamily(Font(R.font.galmuri11_bold, FontWeight.Medium, FontStyle.Normal))

/**
 * Params:
 *     color - 색상 적용
 *     fontSize - 폰트 사이즈
 *     fontStyle - 폰트 스타일 (노멀, 이텔릭)
 *     fontWeight - 두께 설정
 *     fontFamily - 위에 정의된 폰트 종류
 *     letterSpacing - 글자 사이의 간격
 *     textDecoration - 밑줄, 중간줄 윗줄 설정 할 때
 *     textAlign - 정렬
 *     lineHeight - 2줄 이상일때 줄 사이의 간격
 *     overflow - 텍스트의 길이가 화면을 벗어날경우 처리 (Clip 자르기, Ellipsis 생략, )
 * */

val galmuriTyop = Typography(
    defaultFontFamily = galmuri,
    display1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 40.sp,
        lineHeight = 46.sp,
        letterSpacing = 0.5.sp
    ),
    display2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 34.sp,
        lineHeight = 40.sp,
        letterSpacing = 1.sp
    ),
    display3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.8.sp,
    ),
    title1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.2.sp
    ),
    title2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.2.sp
    ),
    title3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.2.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.18.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.2.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 19.sp,
        letterSpacing = 0.38.sp
    ),
    caption1 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.1.sp
    ),
    caption2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.1.sp
    ),
    caption3 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.1.sp
    )
)