package com.jmenmar.ikasi.presentation.screens.vocabulary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.vocabulary_add
import ikasi.composeapp.generated.resources.vocabulary_meaning
import ikasi.composeapp.generated.resources.vocabulary_notes_optional
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddVocabularyView(
    focusManager: FocusManager,
    word: String,
    meaning: String,
    notes: String?,
    onMeaningChange: (String) -> Unit = {},
    onNotesChange: (String) -> Unit = {},
    onAddNewWord: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = word,
                style = MaterialTheme.typography.headlineSmall
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp),
                value = meaning,
                placeholder = {
                    Text(text = stringResource(Res.string.vocabulary_meaning))
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onAny = {
                    focusManager.moveFocus(FocusDirection.Next)
                }),
                onValueChange = { onMeaningChange(it) },
                label = { Text(text = stringResource(Res.string.vocabulary_meaning)) }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp),
                value = notes?: "",
                placeholder = {
                    Text(text = stringResource(Res.string.vocabulary_notes_optional))
                },
                minLines = 5,
                maxLines = 5,
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Text,
                ),
                onValueChange = { onNotesChange(it) },
                label = { Text(text = stringResource(Res.string.vocabulary_notes_optional)) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                enabled = meaning.isNotEmpty() && word.isNotEmpty(),
                shape = RoundedCornerShape(12.dp),
                onClick = { onAddNewWord() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = ""
                    )
                    Text(text = stringResource(Res.string.vocabulary_add))
                }
            }
        }
    }
}