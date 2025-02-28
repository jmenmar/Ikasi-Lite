package com.jmenmar.ikasi.di

import com.jmenmar.ikasi.data.repository.IkasiRepositoryImpl
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import org.koin.dsl.module

val dataModule = module {
    factory<IkasiRepository> { IkasiRepositoryImpl(get()) }
}