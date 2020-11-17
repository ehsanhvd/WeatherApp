package com.hvd.farazpardazan.data.net.model

import com.google.gson.annotations.SerializedName

data class CurrentWeather (
    @SerializedName("weather")
    val weather: List<Weather>
)