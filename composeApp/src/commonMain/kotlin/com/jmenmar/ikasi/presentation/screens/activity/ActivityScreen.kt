package com.jmenmar.ikasi.presentation.screens.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.presentation.components.BasicCard
import com.jmenmar.ikasi.presentation.screens.activity.components.ActivityRecords
import com.jmenmar.ikasi.presentation.screens.activity.components.CalendarView
import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.home_activity
import ikasi.composeapp.generated.resources.home_add
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ActivityScreen(
    navController: NavHostController,
    viewModel: ActivityViewModel = koinViewModel<ActivityViewModel>(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ActivityView(
        activities = state.activities,
        selectedType = state.selectedType,
        onAddClick = {
            viewModel.addActivity()
        },
        onTypeSelected = { type ->
            viewModel.selectType(type = type)
        },
        onDataChange = { date ->
          viewModel.onChangeDate(date = date)
        },
        onNavigateBack = {
            navController.popBackStack()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityView(
    activities: List<Activity> = emptyList(),
    selectedType: ActivityType? = null,
    onAddClick: () -> Unit = {},
    onTypeSelected: (ActivityType) -> Unit = {},
    onDataChange: (Long) -> Unit = {},
    onNavigateBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(Res.string.home_activity))
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    Button(
                        enabled = selectedType != null,
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.primary,
                            disabledContainerColor = MaterialTheme.colorScheme.surface,
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(start = 8.dp, end = 12.dp),
                        onClick = { onAddClick() }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = ""
                            )
                            Text(
                                text = stringResource(Res.string.home_add),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                CalendarView(
                    onDataChange = onDataChange
                )
            }
            item {
                BasicCard(
                    cardModifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -(64.dp))
                        .padding(horizontal = 12.dp)
                ) {
                    Column(
                        modifier = Modifier,
                    ) {
                        if (activities.size < ActivityType.entries.size) {
                            ActivityTypeOptions(
                                selectedType = selectedType,
                                activities = activities,
                                onTypeClick = onTypeSelected
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                        ActivityRecords(
                            activities = activities
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ActivityTypeOptions(
    selectedType: ActivityType? = null,
    activities: List<Activity>,
    onTypeClick: (ActivityType) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            ActivityType.entries.forEach { type ->
                if (activities.none { it.type == type }) {
                    val color = if (selectedType != type) type.color.copy(alpha = 0.3f)
                    else type.color
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = color)
                            .clickable {
                                if (activities.none { it.type == type }) {
                                    onTypeClick(type)
                                }
                            }
                    ) {
                        Icon(
                            modifier = Modifier.padding(8.dp),
                            painter = painterResource(type.icon),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }
            }
        }
    }
}

