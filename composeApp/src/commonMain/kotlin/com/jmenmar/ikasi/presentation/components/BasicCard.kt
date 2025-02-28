package com.jmenmar.ikasi.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BasicCard(
    cardModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    alignment: Alignment. Horizontal = Alignment.Start,
    arrangement: Dp = 12.dp,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    title: String? = null,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Card(
        modifier = cardModifier,
        colors = CardDefaults.cardColors().copy(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = onClick
    ) {
        Column(
            modifier = modifier.padding(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(arrangement),
            horizontalAlignment = alignment,
        ) {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            content()
        }
    }
}