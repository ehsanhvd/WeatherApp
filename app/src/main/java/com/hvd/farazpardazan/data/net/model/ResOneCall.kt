package com.hvd.farazpardazan.data.net.model

import com.google.gson.annotations.SerializedName

data class ResOneCall(
    @SerializedName("current")
    val current: CurrentWeather,

    @SerializedName("daily")
    val daily: List<DailyWeather>,

    @SerializedName("hourly")
    var hourly: List<HourlyWeather>
)