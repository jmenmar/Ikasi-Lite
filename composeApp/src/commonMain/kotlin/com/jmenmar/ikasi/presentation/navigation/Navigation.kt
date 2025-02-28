package com.jmenmar.ikasi.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jmenmar.ikasi.presentation.screens.activity.ActivityScreen
import com.jmenmar.ikasi.presentation.screens.flashcards.FlashcardsScreen
import com.jmenmar.ikasi.presentation.screens.home.HomeScreen
import com.jmenmar.ikasi.presentation.screens.main.MainScreen
import com.jmenmar.ikasi.presentation.screens.today.TodayScreen
import com.jmenmar.ikasi.presentation.screens.vocabulary.VocabularyScreen
import com.jmenmar.ikasi.ui.ThemeMode

@Composable
fun Navigation(
    mainNavController: NavHostController = rememberNavController(),
    theme: ThemeMode,
    onThemeChange: (ThemeMode) -> Unit
) {
    NavHost(
        navController = mainNavController,
        startDestination = NavigationRoute.Main.route
    ) {
        composable(NavigationRoute.Main.route) {
            MainScreen(
                theme = theme,
                onThemeChange = onThemeChange
            )
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    theme: ThemeMode,
    onThemeChange: (ThemeMode) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavRoute.Today.route
    ) {
        composable(route = BottomNavRoute.Home.route) {
            HomeScreen(
                navController = navController,
                innerPadding = innerPadding,
            )
        }
        composable(route = BottomNavRoute.Today.route) {
            TodayScreen(
                innerPadding = innerPadding,
                theme = theme,
                onThemeChange = onThemeChange,
            )
        }
        composable(route = BottomNavRoute.Vocabulary.route) {
            VocabularyScreen(
                innerPadding = innerPadding,
            )
        }
        composable(route = MoreRoute.Flashcards.route) {
            FlashcardsScreen(
                navController = navController
            )
        }
        composable(route = MoreRoute.Activity.route) {
            ActivityScreen(
                navController = navController
            )
        }
    }
}