package com.ali_moh.openweather.di

import com.ali_moh.openweather.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::WeatherViewModel)
}