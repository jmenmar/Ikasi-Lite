package com.jmenmar.ikasi.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.Banner
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.presentation.navigation.MoreRoute
import com.jmenmar.ikasi.presentation.screens.home.components.HomeBannersView
import com.jmenmar.ikasi.presentation.screens.home.components.HomeHeader
import com.jmenmar.ikasi.presentation.screens.home.components.HomeMoreActivities
import com.jmenmar.ikasi.presentation.screens.home.components.RecentActivityCard
import com.jmenmar.ikasi.presentation.utils.ActivityPeriod
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeView(
        innerPadding = innerPadding,
        period = state.period,
        maxValue = state.maxValue,
        activities = state.filteredActivities,
        randomWords = state.randomWords,
        banners = state.banners,
        onPeriodChange = {
            viewModel.changeActivityPeriod(it)
        },
        onRandomizeWords = {
            viewModel.randomizeWords()
        },
        onNavigateToActivity = {
            navController.navigate(MoreRoute.Activity.route)
        },
        onNavigateToFlashcards = {
            navController.navigate(MoreRoute.Flashcards.route)
        }
    )
}

@Composable
fun HomeView(
    innerPadding: PaddingValues,
    period: ActivityPeriod,
    maxValue: Int,
    activities: List<Activity>,
    randomWords: List<Word> = emptyList(),
    banners: List<Banner> = emptyList(),
    onPeriodChange: (ActivityPeriod) -> Unit = {},
    onRandomizeWords: () -> Unit = {},
    onNavigateToActivity: () -> Unit = {},
    onNavigateToFlashcards: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HomeHeader(
            onNavigateToActivity = onNavigateToActivity
        )
        HomeBannersView(
            banners = banners
        )
        RecentActivityCard(
            period = period,
            maxValue = maxValue,
            activities = activities,
            onPeriodChange = onPeriodChange
        )
        HomeMoreActivities(
            randomWords = randomWords,
            randomizeWords = onRandomizeWords,
            onNavigateToFlashcards = onNavigateToFlashcards
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}