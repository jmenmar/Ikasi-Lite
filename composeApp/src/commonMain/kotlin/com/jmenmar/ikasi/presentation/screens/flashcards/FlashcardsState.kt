package com.jmenmar.ikasi.presentation.screens.flashcards

import com.jmenmar.ikasi.domain.model.Word

data class FlashcardsState(
    val vocabulary: List<Word> = emptyList(),
    val isStarted: Boolean = false,
    val isFinished: Boolean = false,
    val isWordShown: Boolean = false,
    val index: Int = 0,
    val correctAnswers: Int = 0,
    val incorrectAnswers: Int = 0
)
