package com.jmenmar.ikasi

import android.app.Application
import com.jmenmar.ikasi.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class IkasiApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@IkasiApp)
        }
    }
}