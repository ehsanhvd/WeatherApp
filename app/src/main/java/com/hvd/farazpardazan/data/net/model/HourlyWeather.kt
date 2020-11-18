package com.hvd.farazpardazan.data.net.model

import com.google.gson.annotations.SerializedName

data class HourlyWeather(
    @SerializedName("dt")
    val timestamp: Long,

    @SerializedName("temp")
    val temp: Float,

    @SerializedName("weather")
    val weather: List<Weather>
)