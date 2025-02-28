package com.jmenmar.ikasi.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.presentation.components.AnimatedLinearProgressIndicator
import com.jmenmar.ikasi.presentation.components.BasicCard
import com.jmenmar.ikasi.presentation.utils.ActivityPeriod
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.home_recent_activity
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RecentActivityCard(
    period: ActivityPeriod,
    maxValue: Int,
    activities: List<Activity>,
    onPeriodChange: (ActivityPeriod) -> Unit = {},
) {
    BasicCard(
        title = stringResource(Res.string.home_recent_activity),
    ) {
        ActivityType.entries.forEach {
            val progress = if (maxValue <= 0) {
                0f
            } else {
                activities.filter { activity -> activity.type == it }.size.toFloat() / maxValue.toFloat()
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = it.color)
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        painter = painterResource(it.icon),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
                Text(
                    modifier = Modifier.weight(3f),
                    text = stringResource(it.title)
                )
                AnimatedLinearProgressIndicator(
                    modifier = Modifier
                        .weight(6f)
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    indicatorProgress = progress,
                    color = it.color,
                    trackColor = MaterialTheme.colorScheme.background,
                    gapSize = 0.dp,
                )
            }
        }
        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActivityPeriod.entries.forEach {
                PeriodChip(
                    modifier = Modifier.weight(1f),
                    activityPeriod = it,
                    selected = it == period,
                    onPeriodChange = onPeriodChange,
                )
            }
        }
    }
}

@Composable
fun PeriodChip(
    modifier: Modifier = Modifier,
    activityPeriod: ActivityPeriod,
    selected: Boolean = false,
    onPeriodChange: (ActivityPeriod) -> Unit = {},
) {
    FilterChip(
        modifier = modifier,
        onClick = { onPeriodChange(activityPeriod) },
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(activityPeriod.title),
                textAlign = TextAlign.Center
            )
        },
        selected = selected,
        colors = FilterChipDefaults.filterChipColors().copy(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}