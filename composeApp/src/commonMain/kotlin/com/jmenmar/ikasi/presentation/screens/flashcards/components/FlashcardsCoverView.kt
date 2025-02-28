package com.jmenmar.ikasi.presentation.screens.flashcards.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.ui.Pink
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.flashcards_add_vocabulary
import ikasi.composeapp.generated.resources.flashcards_no_flashcards
import ikasi.composeapp.generated.resources.flashcards_start
import ikasi.composeapp.generated.resources.home_flashcards
import ikasi.composeapp.generated.resources.note_stack
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FlashcardsCoverView(
    emptyFlashcards: Boolean = false,
    onStartClick: () -> Unit = {},
    onNavigateToVocabulary: () -> Unit = {}
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
                    Icon(
                        modifier = Modifier.size(100.dp),
                        painter = painterResource(Res.drawable.note_stack),
                        contentDescription = "",
                        tint = Pink
                    )
                    Text(
                        text = if (!emptyFlashcards) stringResource(Res.string.home_flashcards)
                        else stringResource(Res.string.flashcards_no_flashcards),
                        style = MaterialTheme.typography.displaySmall,
                        color = Pink
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    if (!emptyFlashcards) {
                        Button(
                            colors = ButtonDefaults.buttonColors().copy(
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(start = 12.dp, end = 14.dp),
                            onClick = onStartClick
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 24.dp),
                                text = stringResource(Res.string.flashcards_start)
                            )
                        }
                    } else {
                        Button(
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(start = 12.dp, end = 14.dp),
                            onClick = onNavigateToVocabulary
                        ) {
                            Text(text = stringResource(Res.string.flashcards_add_vocabulary))
                        }
                    }

                }
            }
        }
    }
}