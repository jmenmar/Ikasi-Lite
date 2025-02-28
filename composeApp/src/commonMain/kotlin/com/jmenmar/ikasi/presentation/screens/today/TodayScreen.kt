package com.jmenmar.ikasi.presentation.screens.today

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.presentation.screens.today.components.TodayActivityRow
import com.jmenmar.ikasi.ui.ThemeMode
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun TodayScreen(
    innerPadding: PaddingValues,
    viewModel: TodayViewModel = koinViewModel<TodayViewModel>(),
    theme: ThemeMode,
    onThemeChange: (ThemeMode) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TodayView(
        theme = theme,
        innerPadding = innerPadding,
        todayActivities = state.todayActivities,
        updateActivity = { checked, type ->
            viewModel.updateActivity(checked, type)
        },
        onThemeChange = onThemeChange
    )
}

@Composable
fun TodayView(
    theme: ThemeMode,
    innerPadding: PaddingValues,
    todayActivities: List<Activity>,
    updateActivity: (Boolean, ActivityType) -> Unit,
    onThemeChange: (ThemeMode) -> Unit,
) {
    var showThemeOptions by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Column(modifier = Modifier.align(Alignment.TopEnd).padding(end = 8.dp)) {
            IconButton(
                modifier = Modifier.size(28.dp),
                onClick = { showThemeOptions = !showThemeOptions },
            ) {
                Icon(
                    painter = painterResource(theme.icon),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            AnimatedVisibility(visible = showThemeOptions) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ThemeMode.entries.forEach { theme ->
                        IconButton(
                            modifier = Modifier.size(28.dp),
                            onClick = {
                                onThemeChange(theme)
                                showThemeOptions = false
                            }
                        ) {
                            Icon(
                                painter = painterResource(theme.icon),
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            ActivityType.entries.forEach { type ->
                TodayActivityRow(
                    type = type,
                    checked = todayActivities.any { it.type == type },
                    updateActivity = updateActivity
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}