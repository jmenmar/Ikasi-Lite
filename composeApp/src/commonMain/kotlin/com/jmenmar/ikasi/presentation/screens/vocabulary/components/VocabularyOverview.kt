package com.jmenmar.ikasi.presentation.screens.vocabulary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.note_stack
import ikasi.composeapp.generated.resources.vocabulary
import ikasi.composeapp.generated.resources.vocabulary_entries
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun VocabularyOverview(
    modifier: Modifier = Modifier,
    totalWords: Int,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .padding(bottom = 48.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Icon(
            modifier = Modifier.size(100.dp),
            painter = painterResource(Res.drawable.note_stack),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.surface
        )
        Text(
            text = stringResource(Res.string.vocabulary),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.surface
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(Res.string.vocabulary_entries, totalWords),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.surface
        )
    }
}