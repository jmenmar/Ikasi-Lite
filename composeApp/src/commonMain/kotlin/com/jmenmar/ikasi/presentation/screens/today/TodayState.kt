package com.jmenmar.ikasi.presentation.screens.today

import com.jmenmar.ikasi.domain.model.Activity

data class TodayState(
    val todayActivities: List<Activity> = emptyList(),
)