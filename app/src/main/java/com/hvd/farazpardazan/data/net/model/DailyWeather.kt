package com.hvd.farazpardazan.data.net.model

import com.google.gson.annotations.SerializedName

data class DailyWeather(
    @SerializedName("weather")
    val weather: List<Weather>,

    @SerializedName("temp")
    val temp: Temp
)