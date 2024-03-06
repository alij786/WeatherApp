package com.ali_moh.openweather

import android.app.Application
import com.ali_moh.openweather.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
            // other Koin configurations
        }
    }
}