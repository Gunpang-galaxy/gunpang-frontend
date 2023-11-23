package com.gunpang.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gunpang.common.R

// fontfamily (수정 필요할 듯)
val gmarketsans = FontFamily(Font(R.font.gmarketsans_medium, FontWeight.Medium, FontStyle.Normal))
val gmarketsansBold = FontFamily(Font(R.font.gmarketsans_bold, FontWeight.Medium, FontStyle.Normal))
val gmarketsansLight = FontFamily(Font(R.font.gmarketsans_light, FontWeight.Medium, FontStyle.Normal))
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
val gmarketsansTypo = Typography(
    displaySmall = TextStyle(
        fontFamily = gmarketsans,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = gmarketsans,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        lineHeight = 29.sp,
        letterSpacing = 0.5.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = gmarketsans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = gmarketsans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = gmarketsans,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = gmarketsans,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = gmarketsans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = gmarketsans,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = gmarketsans,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    )
)

val galmuriTyop = Typography(
    displayLarge = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.5.sp
    ),
    displaySmall = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        lineHeight = 29.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        lineHeight = 29.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Medium,
        fontSize = 25.sp,
        lineHeight = 29.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = galmuri,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 19.sp,
        letterSpacing = 0.5.sp
    )
)