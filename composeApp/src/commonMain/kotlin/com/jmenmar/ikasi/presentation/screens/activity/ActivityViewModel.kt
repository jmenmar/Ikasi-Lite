package com.jmenmar.ikasi.presentation.screens.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.presentation.utils.dayLocalDate
import com.jmenmar.ikasi.presentation.utils.todayLocalDate
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class ActivityViewModel(
    private val ikasiRepository: IkasiRepository
): ViewModel() {
    private val _state = MutableStateFlow(ActivityState())
    val state: StateFlow<ActivityState> = _state

    private var currentJob: Job? = null

    init {
        getActivities(date = todayLocalDate().toEpochDays())
    }

    private fun getActivities(date: Int) {
        _state.value = _state.value.copy(selectedDate = date)
        currentJob?.cancel()
        currentJob = viewModelScope.launch {
            ikasiRepository.getActivities(
                dateFrom = date,
                dateTo = date,
            ).collect {
                _state.value = _state.value.copy(activities = it.sortedBy { it.type.ordinal })
            }
        }
    }

    fun selectType(type: ActivityType) {
        _state.value = _state.value.copy(selectedType = type)
    }

    fun onChangeDate(date: Long) {
        _state.value = _state.value.copy(selectedType = null)
        getActivities(date = dayLocalDate(value = date).toEpochDays())
    }

    fun addActivity() {
        if (state.value.selectedType != null) {
            viewModelScope.launch {
                ikasiRepository.newActivity(
                    Activity(
                        type = state.value.selectedType!!,
                        date = LocalDate.fromEpochDays(state.value.selectedDate)
                    )
                )
            }
        }
        _state.value = _state.value.copy(selectedType = null)
    }
}