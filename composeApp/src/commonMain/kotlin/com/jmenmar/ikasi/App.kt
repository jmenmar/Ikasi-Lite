package com.jmenmar.ikasi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmenmar.ikasi.data.database.rememberDataStore
import com.jmenmar.ikasi.presentation.navigation.Navigation
import com.jmenmar.ikasi.ui.IkasiTheme
import com.jmenmar.ikasi.ui.ThemeMode
import com.jmenmar.ikasi.ui.rememberThemeSource
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val datastore = rememberDataStore()
    val themeSource = rememberThemeSource(prefs = datastore)

    val theme by themeSource.getThemeMode().collectAsStateWithLifecycle(ThemeMode.System)
    val scope = rememberCoroutineScope()

    IkasiTheme(mode = theme) {
        Navigation(
            theme = theme,
            onThemeChange = { mode ->
                scope.launch {
                    themeSource.changeTheme(mode)
                }
            }
        )
    }
}