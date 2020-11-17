package com.hvd.farazpardazan.data.net.model

import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("day")
    val day: Float,

    @SerializedName("max")
    val max: Float,

    @SerializedName("min")
    val min: Float,

    @SerializedName("night")
    val night: Float,

    @SerializedName("eve")
    val evening: Float,

    @SerializedName("morn")
    val morning: Float
)