package com.jmenmar.ikasi.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.core.allBanners
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import com.jmenmar.ikasi.presentation.utils.ActivityPeriod
import com.jmenmar.ikasi.presentation.utils.beforeDate
import com.jmenmar.ikasi.presentation.utils.toLocalDate
import com.jmenmar.ikasi.presentation.utils.todayInstant
import com.jmenmar.ikasi.presentation.utils.todayLocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus

class HomeViewModel(
    private val ikasiRepository: IkasiRepository
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        getActivities()
        randomizeWords()
    }

    private fun getActivities() {
        viewModelScope.launch {
            ikasiRepository.getActivities(
                dateFrom = beforeDate(value = 1, unit = DateTimeUnit.YEAR),
                dateTo = todayLocalDate().toEpochDays()
            ).collect { allActivities ->
                updateState(allActivities)
                loadBanners()
            }
        }
    }

    private fun updateState(allActivities: List<Activity>) {
        val filtered = filterActivitiesByPeriod(allActivities, state.value.period)
        _state.value = state.value.copy(
            totalActivities = allActivities,
            filteredActivities = filtered,
            maxValue = filtered.groupBy { it.type }.maxByOrNull { it.value.size }?.value?.size
                ?: 0,
        )
    }

    private fun filterActivitiesByPeriod(allActivities: List<Activity>, period: ActivityPeriod): List<Activity> {
        return allActivities.filter {
            it.date >= todayInstant().minus(
                value = period.value,
                unit = period.unit,
                timeZone = TimeZone.currentSystemDefault()
            ).toLocalDate()
        }
    }

    fun changeActivityPeriod(period: ActivityPeriod) {
        _state.value = _state.value.copy(
            period = period,
        )
        updateState(state.value.totalActivities)
    }

    private fun loadBanners() {
        _state.value = _state.value.copy(banners = allBanners)
    }

    fun randomizeWords() {
        viewModelScope.launch {
            val words = withContext(Dispatchers.IO) {
                ikasiRepository.getRandomWords(length = 3)
            }
            _state.value = _state.value.copy(randomWords = words)
        }
    }
}