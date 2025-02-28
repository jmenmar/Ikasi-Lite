package com.jmenmar.ikasi.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomNavBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavRoute.Home,
        BottomNavRoute.Today,
        BottomNavRoute.Vocabulary,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar(
            tonalElevation = 0.dp
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavRoute,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(
                text = stringResource(screen.title),
                fontSize = 14.sp
            )
        },
        icon = {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = stringResource(screen.title),
                modifier = Modifier.size(26.dp)
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        colors = NavigationBarItemDefaults.colors().copy(
            selectedIndicatorColor = Color.Transparent,
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            unselectedTextColor = MaterialTheme.colorScheme.secondary,
        ),
        onClick = {
            navController.navigate(route = screen.route) {
                navController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        },
    )
}