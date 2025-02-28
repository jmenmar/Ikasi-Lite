package com.jmenmar.ikasi.presentation.screens.activity.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jmenmar.ikasi.presentation.utils.toLocalDate
import kotlinx.datetime.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarView(
    onDataChange: (Long) -> Unit = {}
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds(),
        selectableDates = PastOrPresentSelectableDates
    )
    val selectedDate = datePickerState.selectedDateMillis

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .offset(y = (-50).dp)
    ) {
        DatePicker(
            title = {},
            state = datePickerState,
            showModeToggle = false,
            colors = DatePickerDefaults.colors().copy(
                containerColor = MaterialTheme.colorScheme.background,
            )
        )
    }

    LaunchedEffect(selectedDate) {
        onDataChange(selectedDate!!)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
object PastOrPresentSelectableDates: SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis <= Clock.System.now().toEpochMilliseconds()
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year <= Clock.System.now().toLocalDate().year
    }
}