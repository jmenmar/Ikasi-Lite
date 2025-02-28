package com.jmenmar.ikasi.presentation.screens.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.home
import ikasi.composeapp.generated.resources.home_activity
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeHeader(
    onNavigateToActivity: () -> Unit = {}
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(Res.string.home),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f)
        )
        Button(
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(start = 8.dp, end = 12.dp),
            onClick = { onNavigateToActivity() }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
                Text(text = stringResource(Res.string.home_activity))
            }
        }
    }
}