package com.hvd.farazpardazan.di

import com.google.gson.Gson
import com.hvd.farazpardazan.Consts
import com.hvd.farazpardazan.data.net.WeatherRestApi
import com.hvd.farazpardazan.util.LogInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun createClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(LogInterceptor())
        .build()

    @Singleton
    @Provides
    fun createRestApi(client: OkHttpClient, gson : Gson) : WeatherRestApi = Retrofit.Builder()
        .baseUrl(Consts.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build().create(WeatherRestApi::class.java)
}