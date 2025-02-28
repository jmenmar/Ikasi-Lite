package com.jmenmar.ikasi.presentation.screens.activity.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.domain.model.Activity
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.activities
import ikasi.composeapp.generated.resources.pencil
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ActivityRecords(
    activities: List<Activity>
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Text(text = stringResource(Res.string.activities))
    }
    HorizontalDivider()
    activities.forEach { activity ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = activity.type.color)
            ) {
                Icon(
                    modifier = Modifier.size(32.dp).padding(4.dp),
                    painter = painterResource(Res.drawable.pencil),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
            Text(text = stringResource(activity.type.title))
        }
        HorizontalDivider()
    }
}