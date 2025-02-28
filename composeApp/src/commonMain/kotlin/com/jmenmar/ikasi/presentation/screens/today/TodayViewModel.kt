package com.jmenmar.ikasi.presentation.screens.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.presentation.utils.beforeDate
import com.jmenmar.ikasi.presentation.utils.todayLocalDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit

class TodayViewModel(
    private val ikasiRepository: IkasiRepository
): ViewModel() {
    private val _state = MutableStateFlow(TodayState())
    val state: StateFlow<TodayState> = _state

    init {
        getActivities()
    }

    private fun getActivities() {
        viewModelScope.launch {
            ikasiRepository.getActivities(
                dateFrom = beforeDate(value = 7, unit = DateTimeUnit.DAY),
                dateTo = todayLocalDate().toEpochDays()
            ).collect { allActivities ->
                updateState(allActivities)
            }
        }
    }

    private fun updateState(allActivities: List<Activity>) {
        _state.value = state.value.copy(
            todayActivities = allActivities.filter { activity ->
                activity.date == todayLocalDate()
            },
        )
    }

    fun updateActivity(checked: Boolean, type: ActivityType) {
        val activity = Activity(type = type, date = todayLocalDate())
        if (checked) {
            deleteActivity(activity)
        } else {
            addActivity(activity)
        }
    }

    private fun addActivity(activity: Activity) {
        viewModelScope.launch {
            ikasiRepository.newActivity(activity)
        }
    }

    private fun deleteActivity(activity: Activity) {
        viewModelScope.launch {
            ikasiRepository.deleteActivity(activity)
        }
    }
}