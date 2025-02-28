package com.jmenmar.ikasi.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.jmenmar.ikasi.data.dao.ActivityDao
import com.jmenmar.ikasi.data.dao.WordDao
import com.jmenmar.ikasi.data.model.ActivityEntity
import com.jmenmar.ikasi.data.model.WordEntity

const val DATABASE_NAME = "ikasi_db.db"

expect object IkasiCTor : RoomDatabaseConstructor<IkasiDatabase>

@Database(
    entities = [ActivityEntity::class, WordEntity::class],
    version = 1,
)
@ConstructedBy(IkasiCTor::class)
abstract class IkasiDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDao
    abstract fun wordDao(): WordDao
}