package com.gunpang.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

// 다크 모드와 관련된 코드는 주석 처리

//private val DarkColorScheme = darkColorScheme(
//    primary = Navy500,
//    secondary = Gray500,
//    tertiary = Pink100
//)

class BottomNavTheme {
    val selectedIconColor: Color = Gray800
    val selectedTextColor: Color = Gray800
    val selectedIndicatorColor: Color = Navy500
    val unselectedIconColor: Color = Gray500
    val unselectedTextColor: Color = Gray500
}

private val LightColorScheme = lightColorScheme(
    primary = Navy500,
    secondary = Gray500,
    tertiary = Pink100,
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun GunpangTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = gmarketsansTypo,
        content = content
    )
}

