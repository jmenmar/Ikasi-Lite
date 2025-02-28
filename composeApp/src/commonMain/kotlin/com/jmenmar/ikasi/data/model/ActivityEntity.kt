package com.jmenmar.ikasi.data.model

import androidx.room.Entity
import com.jmenmar.ikasi.domain.model.Activity
import com.jmenmar.ikasi.domain.model.ActivityType
import kotlinx.datetime.LocalDate

@Entity(
    tableName = "activity",
    primaryKeys = ["type", "date"]
)
data class ActivityEntity(
    val type: ActivityType,
    val date: Int,
) {
    companion object {
        fun ActivityEntity.toDomain() = Activity(
            type = type,
            date = LocalDate.fromEpochDays(this.date)
        )
    }
}
