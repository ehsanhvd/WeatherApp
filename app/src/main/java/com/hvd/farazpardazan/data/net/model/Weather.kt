package com.hvd.farazpardazan.data.net.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id")
    var id: Long,

    @SerializedName("main")
    var main : String,

    @SerializedName("description")
    var desc : String,

    @SerializedName("icon")
    var icon : String
)