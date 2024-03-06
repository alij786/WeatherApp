package com.ali_moh.openweather.di

import com.ali_moh.openweather.data.PreviousSearchDataStore
import com.ali_moh.openweather.repository.WeatherRepository
import com.ali_moh.openweather.repository.WeatherRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repoModule = module {
    singleOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() }
    single { PreviousSearchDataStore(androidApplication()) }
}