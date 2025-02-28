package com.jmenmar.ikasi.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.presentation.components.BasicCard
import com.jmenmar.ikasi.ui.Pink
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.home_flashcards
import ikasi.composeapp.generated.resources.home_flashcards_description
import ikasi.composeapp.generated.resources.note_stack
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FlashcardsCard(
    modifier: Modifier = Modifier,
    onNavigateToFlashcards: () -> Unit = {}
) {
    BasicCard(
        cardModifier = modifier,
        arrangement = 5.dp,
        onClick = onNavigateToFlashcards
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Pink.copy(alpha = 0.2f))
        ) {
            Icon(
                modifier = Modifier.padding(6.dp).size(20.dp),
                painter = painterResource(Res.drawable.note_stack),
                contentDescription = "",
                tint = Pink,
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = stringResource(Res.string.home_flashcards),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(Res.string.home_flashcards_description),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
        )
    }
}