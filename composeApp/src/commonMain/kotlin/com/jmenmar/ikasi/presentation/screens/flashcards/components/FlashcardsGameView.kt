package com.jmenmar.ikasi.presentation.screens.flashcards.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.presentation.components.BasicCard
import com.jmenmar.ikasi.ui.GreyMedium
import com.jmenmar.ikasi.ui.White
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.flashcards_correct
import ikasi.composeapp.generated.resources.flashcards_incorrect
import ikasi.composeapp.generated.resources.flashcards_show_meaning
import ikasi.composeapp.generated.resources.note_stack
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FlashcardsGameView(
    isWordShown: Boolean = false,
    word: Word,
    onShowMeaningClick: () -> Unit = {},
    onNextWordClick: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    if (isWordShown) {
                        VisibleFlashcardView(word = word)
                    } else {
                        HiddenFlashcardView(word = word)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    if (isWordShown) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                colors = ButtonDefaults.buttonColors().copy(
                                    containerColor = GreyMedium
                                ),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(start = 12.dp, end = 14.dp),
                                onClick = { onNextWordClick(false) }
                            ) {
                                Text(text = stringResource(Res.string.flashcards_incorrect))
                            }
                            Button(
                                colors = ButtonDefaults.buttonColors().copy(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(start = 12.dp, end = 14.dp),
                                onClick = { onNextWordClick(true) }
                            ) {
                                Text(text = stringResource(Res.string.flashcards_correct))
                            }
                        }
                    } else {
                        Button(
                            colors = ButtonDefaults.buttonColors().copy(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(start = 12.dp, end = 14.dp),
                            onClick = onShowMeaningClick
                        ) {
                            Text(text = stringResource(Res.string.flashcards_show_meaning))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HiddenFlashcardView(
    word: Word
) {
    BasicCard(
        cardModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .heightIn(min = 200.dp, max = 200.dp),
        modifier = Modifier.fillMaxWidth(),
        alignment = Alignment.CenterHorizontally,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        Column {
            Icon(
                modifier = Modifier.size(75.dp),
                painter = painterResource(Res.drawable.note_stack),
                contentDescription = "",
                tint = White
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = word.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun VisibleFlashcardView(
    word: Word
) {
    BasicCard(
        cardModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .heightIn(min = 200.dp, max = 400.dp),
        modifier = Modifier.fillMaxWidth(),
        alignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            painter = painterResource(Res.drawable.note_stack),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = word.title,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = word.meaning,
            style = MaterialTheme.typography.titleLarge
        )
        word.notes?.let {
            Text(
                text = word.notes,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}