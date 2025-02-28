package com.jmenmar.ikasi.presentation.screens.activity

import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.presentation.utils.todayLocalDate

data class ActivityState(
    val activities: List<Activity> = emptyList(),
    val selectedType: ActivityType? = null,
    val selectedDate: Int = todayLocalDate().toEpochDays()
)
