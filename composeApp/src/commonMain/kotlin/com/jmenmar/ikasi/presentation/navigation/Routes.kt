package com.jmenmar.ikasi.presentation.navigation

import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.check_circle
import ikasi.composeapp.generated.resources.home
import ikasi.composeapp.generated.resources.note_stack
import ikasi.composeapp.generated.resources.today
import ikasi.composeapp.generated.resources.vocabulary
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class NavigationRoute(val route: String) {
    data object Main : NavigationRoute("main")
}

sealed class BottomNavRoute(
    val route: String,
    val title: StringResource,
    val icon: DrawableResource
) {
    data object Home : BottomNavRoute(
        route = "HOME",
        title = Res.string.home,
        icon = Res.drawable.home
    )

    data object Today : BottomNavRoute(
        route = "TODAY",
        title = Res.string.today,
        icon = Res.drawable.check_circle
    )

    data object Vocabulary : BottomNavRoute(
        route = "VOCABULARY",
        title = Res.string.vocabulary,
        icon = Res.drawable.note_stack
    )
}

sealed class MoreRoute(val route: String) {
    data object Flashcards : MoreRoute("FLASHCARDS")
    data object Activity : MoreRoute("ACTIVITY")

}