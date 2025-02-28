package com.jmenmar.ikasi.presentation.screens.today.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.ui.GreyMedium
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.check_circle
import ikasi.composeapp.generated.resources.uncheck
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TodayActivityRow(
    type: ActivityType,
    checked: Boolean,
    updateActivity: (Boolean, ActivityType) -> Unit = { _, _ -> },
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { updateActivity(checked, type) }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = type.color)
        ) {
            Icon(
                modifier = Modifier.padding(12.dp),
                painter = painterResource(type.icon),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = stringResource(type.title),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                type.skills.forEach { skill ->
                    Box(
                        modifier = Modifier
                            .border(width = 1.dp, color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(6.dp))
                            .clip(RoundedCornerShape(6.dp))
                            .background(color = MaterialTheme.colorScheme.surface),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            text = stringResource(skill),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        )
                    }
                }
            }
        }
        Icon(
            modifier = Modifier.size(30.dp),
            painter = if (checked) painterResource(Res.drawable.check_circle)
            else painterResource(Res.drawable.uncheck),
            contentDescription = "",
            tint = if (checked) type.color else GreyMedium,
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}