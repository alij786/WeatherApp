package com.ali_moh.openweather.data

import com.google.gson.annotations.SerializedName

data class WeatherDataModel(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("main")
    val main: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("icon")
    val icon: String? = null,
)
