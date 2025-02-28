package com.jmenmar.ikasi.data.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getDatabase(context: Context): IkasiDatabase {
    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder<IkasiDatabase>(context, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}