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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.presentation.components.BasicCard
import com.jmenmar.ikasi.ui.Blue
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.flashcards_add_vocabulary
import ikasi.composeapp.generated.resources.home_no_words_enough
import ikasi.composeapp.generated.resources.home_writing_vocabulary
import ikasi.composeapp.generated.resources.pencil
import ikasi.composeapp.generated.resources.writing
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RandomWritingCard(
    modifier: Modifier = Modifier,
    randomWords: List<Word> = emptyList(),
    randomizeWords: () -> Unit = {},
) {
    var showFrontSide by remember { mutableStateOf(true) }

    BasicCard(
        cardModifier = modifier,
        arrangement = 5.dp,
        onClick = {
            showFrontSide = !showFrontSide
            if (!showFrontSide) {
                randomizeWords()
            }
        }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Blue.copy(alpha = 0.2f))
        ) {
            Icon(
                modifier = Modifier.padding(6.dp).size(20.dp),
                painter = painterResource(Res.drawable.pencil),
                contentDescription = "",
                tint = Blue,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        if (showFrontSide) {
            Text(
                text = stringResource(Res.string.writing),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(Res.string.home_writing_vocabulary),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            )
        } else {
            if (randomWords.isNotEmpty()) {
                randomWords.forEachIndexed { index, word ->
                    Text(
                        text = "${index+1}. ${word.title}",
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            } else {
                Text(
                    text = stringResource(Res.string.home_no_words_enough),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = stringResource(Res.string.flashcards_add_vocabulary),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                )
            }
        }
    }
}