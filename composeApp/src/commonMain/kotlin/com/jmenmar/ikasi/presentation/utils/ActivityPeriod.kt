package com.jmenmar.ikasi.presentation.utils

import ikasi.composeapp.generated.resources.Res
import ikasi.composeapp.generated.resources.last_week
import ikasi.composeapp.generated.resources.one_month
import ikasi.composeapp.generated.resources.three_months
import kotlinx.datetime.DateTimeUnit
import org.jetbrains.compose.resources.StringResource

enum class ActivityPeriod(
    val title: StringResource,
    val value: Int,
    val unit: DateTimeUnit
) {
    ONE_WEEK(
        title = Res.string.last_week,
        value = 7,
        unit = DateTimeUnit.DAY
    ),
    ONE_MONTH(
        title = Res.string.one_month,
        value = 1,
        unit = DateTimeUnit.MONTH
    ),
    THREE_MONTHS(
        title = Res.string.three_months,
        value = 3,
        unit = DateTimeUnit.MONTH
    ),
}