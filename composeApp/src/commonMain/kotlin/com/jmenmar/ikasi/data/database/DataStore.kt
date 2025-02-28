package com.jmenmar.ikasi.data.database

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

const val dataStoreFileName = "ikasi.preferences_pb"

typealias PrefsDataStore = DataStore<Preferences>

fun createDataStore(producePath: () -> String): PrefsDataStore =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )

@Composable
expect fun rememberDataStore(): PrefsDataStore