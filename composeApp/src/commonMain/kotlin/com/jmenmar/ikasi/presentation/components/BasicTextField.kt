package com.jmenmar.ikasi.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BasicTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    value: String,
    placeholder: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    foregroundColor: Color = MaterialTheme.colorScheme.onSurface,
    onValueChange: (String) -> Unit = {},
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled,
        value = value,
        placeholder = {
            Text(text = placeholder)
        },
        trailingIcon = trailingIcon,
        singleLine = true,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor,
            focusedTextColor = foregroundColor,
            unfocusedTextColor = foregroundColor,
            focusedPlaceholderColor = foregroundColor.copy(alpha = 0.4f),
            unfocusedPlaceholderColor = foregroundColor.copy(alpha = 0.4f),
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}