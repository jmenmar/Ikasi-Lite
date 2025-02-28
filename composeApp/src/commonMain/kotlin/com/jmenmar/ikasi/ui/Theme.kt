package com.jmenmar.ikasi.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun IkasiTheme(
    mode: ThemeMode = ThemeMode.System,
    content: @Composable () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val colorScheme = remember(mode) {
        when (mode) {
            ThemeMode.Light -> LightColorScheme
            ThemeMode.Dark -> DarkColorScheme
            ThemeMode.System -> if (isDark) DarkColorScheme else LightColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
//        typography = AppTypography(),
//        shapes = Shapes,
        content = content,
    )
}