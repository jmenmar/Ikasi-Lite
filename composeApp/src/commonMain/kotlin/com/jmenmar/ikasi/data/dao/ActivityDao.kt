package com.jmenmar.ikasi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmenmar.ikasi.data.model.ActivityEntity
import com.jmenmar.ikasi.domain.model.ActivityType
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {
    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: ActivityEntity)

    // SELECT
    @Query("SELECT * FROM activity WHERE date >= :dateFrom AND date <= :dateTo")
    fun getActivities(dateFrom: Int, dateTo: Int): Flow<List<ActivityEntity>>

    // DELETE
    @Query("DELETE FROM activity WHERE type = :type AND date = :date")
    suspend fun deleteActivity(type: ActivityType, date: Int)
}