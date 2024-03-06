package com.ali_moh.openweather.di

import org.koin.dsl.module

val appModule = module {
    includes(
        repoModule,
        viewModelModule,
    )
}