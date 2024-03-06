package com.ali_moh.openweather.repository

import com.ali_moh.openweather.viewmodel.UIState
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherDetails(locationQuery: String?): Flow<UIState>
}