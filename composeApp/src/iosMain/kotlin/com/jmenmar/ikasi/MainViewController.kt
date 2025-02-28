package com.jmenmar.ikasi

import androidx.compose.ui.window.ComposeUIViewController
import com.jmenmar.ikasi.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}