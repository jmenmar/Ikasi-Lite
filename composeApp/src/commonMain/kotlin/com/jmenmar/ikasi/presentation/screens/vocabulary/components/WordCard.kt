package com.jmenmar.ikasi.presentation.screens.vocabulary.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Word
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.ic_visibility
import ikasi.composeapp.generated.resources.ic_visibility_off
import org.jetbrains.compose.resources.painterResource

@Composable
fun WordCard(
    word: Word,
    onDeleteClick: (Word) -> Unit
) {
    var isShown by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = { isShown = !isShown }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .animateContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = if (isShown) painterResource(Res.drawable.ic_visibility_off)
                else painterResource(Res.drawable.ic_visibility),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = null
            )
            Column(modifier = Modifier.weight(1f)) {
                if (isShown) {
                    Text(
                        text = word.title,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = word.meaning,
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (!word.notes.isNullOrEmpty()) {
                        Text(
                            text = word.notes,
                            style = MaterialTheme.typography.bodySmall,
                            fontStyle = FontStyle.Italic
                        )
                    }
                } else {
                    Text(
                        text = word.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            if (isShown) {
                Icon(
                    modifier = Modifier.clickable { onDeleteClick(word) },
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}