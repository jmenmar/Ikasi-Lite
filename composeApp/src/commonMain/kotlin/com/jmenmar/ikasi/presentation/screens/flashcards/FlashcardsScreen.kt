package com.jmenmar.ikasi.presentation.screens.flashcards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.presentation.navigation.BottomNavRoute
import com.jmenmar.ikasi.presentation.screens.flashcards.components.FlashcardsCoverView
import com.jmenmar.ikasi.presentation.screens.flashcards.components.FlashcardsFinishView
import com.jmenmar.ikasi.presentation.screens.flashcards.components.FlashcardsGameView
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.home_flashcards
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun FlashcardsScreen(
    navController: NavHostController,
    viewModel: FlashcardsViewModel = koinViewModel<FlashcardsViewModel>()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FlashcardsView(
        isStarted = state.isStarted,
        isFinished = state.isFinished,
        isWordShown = state.isWordShown,
        index = state.index,
        vocabulary = state.vocabulary,
        correct = state.correctAnswers,
        incorrect = state.incorrectAnswers,
        onStartClick = { viewModel.startFlashcards() },
        onShowMeaningClick = { viewModel.onShowMeaning() },
        onNextWordClick = { viewModel.onNextWord(it) },
        onNavigateToVocabulary = {
            navController.navigate(BottomNavRoute.Vocabulary.route) {
                popUpTo(BottomNavRoute.Today.route) {
                    inclusive = false
                }
            }
        },
        onNavigateBack = {
            navController.popBackStack()
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashcardsView(
    isStarted: Boolean = false,
    isFinished: Boolean = false,
    isWordShown: Boolean = false,
    index: Int = 0,
    vocabulary: List<Word> = emptyList(),
    correct: Int = 0,
    incorrect: Int = 0,
    onStartClick: () -> Unit = {},
    onShowMeaningClick: () -> Unit = {},
    onNextWordClick: (Boolean) -> Unit = {},
    onNavigateToVocabulary: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(Res.string.home_flashcards))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                isFinished ->
                    FlashcardsFinishView(
                        correct = correct,
                        incorrect = incorrect
                    )

                isStarted ->
                    FlashcardsGameView(
                        isWordShown = isWordShown,
                        word = vocabulary[index],
                        onShowMeaningClick = onShowMeaningClick,
                        onNextWordClick = onNextWordClick,
                    )

                else ->
                    FlashcardsCoverView(
                        emptyFlashcards = vocabulary.isEmpty(),
                        onStartClick = onStartClick,
                        onNavigateToVocabulary = onNavigateToVocabulary
                    )
            }
        }
    }
}