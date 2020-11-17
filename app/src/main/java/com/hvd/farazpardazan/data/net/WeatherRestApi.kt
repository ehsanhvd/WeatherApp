package com.hvd.farazpardazan.data.net

import com.hvd.farazpardazan.Consts
import com.hvd.farazpardazan.data.net.model.ResOneCall
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRestApi {

    @GET("data/2.5/onecall")
    fun currentWeather(@Query("lat") lat : Double,
                       @Query("lon") lng : Double,
                       @Query("exclude") exclude : String = "alerts,minutely",
                       @Query("appid") key: String = Consts.WEATHER_KEY,
                       @Query("units") units: String = "metric") : Observable<ResOneCall>
}