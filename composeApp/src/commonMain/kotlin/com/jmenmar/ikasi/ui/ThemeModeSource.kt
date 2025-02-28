package com.jmenmar.ikasi.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jmenmar.ikasi.data.database.PrefsDataStore
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.dark_mode
import ikasi.composeapp.generated.resources.light_mode
import ikasi.composeapp.generated.resources.system_mode
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.DrawableResource

class ThemeModeSource(
    private val dataStore: PrefsDataStore
) {
    companion object {
        private const val THEME_KEY = "themeKey"
    }

    fun getThemeMode(): Flow<ThemeMode> {
        return dataStore.data.map {
            val theme = it[stringPreferencesKey(THEME_KEY)] ?: ThemeMode.System.name
            ThemeMode.valueOf(theme)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun changeTheme(
        mode: ThemeMode
    ) {
        return withContext(Dispatchers.IO) {
            try {
                dataStore.edit {
                    it[stringPreferencesKey(THEME_KEY)] = mode.name
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                e.printStackTrace()
            }
        }
    }
}

@Composable
fun rememberThemeSource(
    prefs: PrefsDataStore
): ThemeModeSource {
    return remember { ThemeModeSource(prefs) }
}

enum class ThemeMode(val icon: DrawableResource) {
    Light(icon = Res.drawable.light_mode),
    System(icon = Res.drawable.system_mode),
    Dark(icon = Res.drawable.dark_mode)
}