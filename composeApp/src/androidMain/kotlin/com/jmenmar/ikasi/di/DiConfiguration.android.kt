package com.jmenmar.ikasi.di

import com.jmenmar.ikasi.data.database.IkasiDatabase
import com.jmenmar.ikasi.data.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module {
        single<IkasiDatabase> { getDatabase(get()) }
    }
}