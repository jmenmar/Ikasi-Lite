package com.jmenmar.ikasi.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedLinearProgressIndicator(
    modifier: Modifier = Modifier,
    indicatorProgress: Float,
    color: Color,
    trackColor: Color,
    gapSize: Dp = 0.dp,
) {
    var progress by remember { mutableStateOf(0F) }
    val progressAnimDuration = 1_500
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
    )
    LinearProgressIndicator(
        progress = { progressAnimation },
        modifier = modifier,
        color = color,
        trackColor = trackColor,
        gapSize = gapSize,
        drawStopIndicator = {},
    )
    LaunchedEffect(indicatorProgress) {
        progress = indicatorProgress
    }
}