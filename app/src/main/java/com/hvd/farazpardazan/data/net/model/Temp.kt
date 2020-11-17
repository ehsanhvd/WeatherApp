package com.hvd.farazpardazan.data.net.model

import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("day")
    val day: Int,

    @SerializedName("max")
    val max: Int,

    @SerializedName("min")
    val min: Int,

    @SerializedName("night")
    val night: Int,

    @SerializedName("eve")
    val evening: Int,

    @SerializedName("morn")
    val morning: Int
)