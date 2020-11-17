package com.hvd.farazpardazan.data.net.model

import com.google.gson.annotations.SerializedName

data class HourlyWeather(
    @SerializedName("weather")
    val weather: List<Weather>
)