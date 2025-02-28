package com.jmenmar.ikasi.ui

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val White = Color(0xFFF5F7F9)

val GreyLight = Color(0xFFE3E6E8)
val GreyMedium = Color(0xFFA9AAAC)
val GreyDarkest = Color(0xFF171717)

val BlueDarkest = Color(0xFF212231)
val BlueDark = Color(0xFF292C3D)
val BlueMedium = Color(0xFF5194F7)

val Blue = Color(0xFF5EB0E3)
val Green = Color(0xFF64C29C)
val Yellow = Color(0xFFF3B455)
val Orange = Color(0xFFEE804A)
val Pink = Color(0xFFDF5284)

internal val LightColorScheme = lightColorScheme(
    primary = BlueMedium,
    onPrimary = White,
    background = GreyLight,
    surface = White,
    onSurface = GreyDarkest,
    secondary = GreyMedium,
    tertiary = White,
    onTertiary = GreyDarkest,
    surfaceContainer = White,
)

internal val DarkColorScheme = darkColorScheme(
    primary = BlueMedium,
    onPrimary = White,
    background = BlueDarkest,
    surface = BlueDark,
    onSurface = White,
    secondary = GreyMedium,
    tertiary = BlueDark,
    onTertiary = GreyDarkest,
    surfaceContainer = BlueDark,
)