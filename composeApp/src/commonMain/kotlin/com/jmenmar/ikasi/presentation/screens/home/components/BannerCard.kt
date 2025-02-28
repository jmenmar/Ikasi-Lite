package com.jmenmar.ikasi.presentation.screens.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Banner

@Composable
fun BannerCard(
    modifier: Modifier = Modifier,
    banner: Banner,
) {
    val shapeColor = Color.Black.copy(alpha = 0.3f)
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = banner.type.color,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Canvas(modifier = Modifier.matchParentSize()) {
                val canvasWidth = size.width + 240f
                val canvasHeight = size.height + 240f
                val ellipseWidth = canvasHeight
                val ellipseHeight = canvasHeight
                val ellipseLeft = canvasWidth - ellipseWidth - 120f
                val ellipseTop = 0f

                val path = Path().apply {
                    addOval(
                        oval = Rect(
                            offset = Offset(ellipseLeft, ellipseTop),
                            size = Size(ellipseWidth, ellipseHeight)
                        )
                    )
                }
                drawPath(path, color = shapeColor, style = Fill)
            }
            Column(
                modifier = Modifier.align(Alignment.TopStart),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = banner.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = banner.description,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}