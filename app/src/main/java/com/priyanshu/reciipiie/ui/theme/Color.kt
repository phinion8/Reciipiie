package com.priyanshu.reciipiie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val white = Color(0xFFFFFFFF)
val darkBlue = Color(0xFF0c0f14)
val blue = Color(0xFF5E76FF)
val darkGrey = Color(0xFF161616)
val lightGrey = Color(0xFF868686)
val greyShade2 = Color(0xFFE6E6E6)


val primaryColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) white else darkBlue

val secondaryColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) darkBlue else white

val grey: Color
    @Composable
    get() = if (isSystemInDarkTheme()) darkGrey else greyShade2

val grey300: Color
    @Composable
    get() = if (isSystemInDarkTheme()) lightGrey else greyShade2