package com.ali_moh.openweather

import com.ali_moh.openweather.data.LatLongCoordinatesModel
import com.ali_moh.openweather.data.MainDataModel
import com.ali_moh.openweather.data.OpenWeatherResponseModel
import com.ali_moh.openweather.data.PreviousSearchDataStore
import com.ali_moh.openweather.data.WeatherDataModel
import com.ali_moh.openweather.repository.WeatherRepository
import com.ali_moh.openweather.viewmodel.UIState
import com.ali_moh.openweather.viewmodel.WeatherViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import kotlin.test.assertEquals

class WeatherViewModelTest {
    private val repository = mockk<WeatherRepository>(relaxed = true)
    private val dataStore = mockk<PreviousSearchDataStore>(relaxed = true)

    private val successCall = OpenWeatherResponseModel(
        name = "Vero Beach",
        coordinates = LatLongCoordinatesModel(
            27.6386,
            -80.3973,
        ),
        weatherData = listOf(
            WeatherDataModel(
                description = "scattered clouds",
                icon = "03n",
            )
        ),
        main = MainDataModel(
            temp = 296.84,
            feelsLikeTemp = 297.12,
            pressure = 1011,
            humidity = 71,
        ),
        visibility = 10000,
    )

    private val failCall = "error message"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `verify correct data is presented when call is successful`() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val sut = WeatherViewModel(repository, dataStore)
        coEvery { repository.getWeatherDetails("Vero Beach") } returns flowOf(UIState.Success(data = successCall))
        sut.updateSearchText("Vero Beach")
        sut.fetchWeatherDetails()
        assertEquals(UIState.Success::class.java, sut.weatherResponseState.value::class.java)
        assertEquals(UIState.Success(data = successCall), sut.weatherResponseState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `verify failure state returns correct data`() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val sut = WeatherViewModel(repository, dataStore)
        coEvery { repository.getWeatherDetails("Vero Beach") } returns flowOf(UIState.Failure(error = failCall))
        sut.updateSearchText("Vero Beach")
        sut.fetchWeatherDetails()
        assertEquals(UIState.Failure::class.java, sut.weatherResponseState.value::class.java)
        assertEquals(UIState.Failure(failCall), sut.weatherResponseState.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `verify empty default state`() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val sut = WeatherViewModel(repository, dataStore)
        assertEquals(UIState.Empty::class.java, sut.weatherResponseState.value::class.java)
    }
}