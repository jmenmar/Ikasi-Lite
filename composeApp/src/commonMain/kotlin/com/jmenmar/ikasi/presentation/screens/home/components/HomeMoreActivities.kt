package com.jmenmar.ikasi.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Word

@Composable
fun HomeMoreActivities(
    randomWords: List<Word> = emptyList(),
    randomizeWords: () -> Unit = {},
    onNavigateToFlashcards: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        RandomWritingCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .fillMaxHeight(),
            randomWords = randomWords,
            randomizeWords = randomizeWords,
        )
        FlashcardsCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .fillMaxHeight(),
            onNavigateToFlashcards = onNavigateToFlashcards
        )
    }
}

