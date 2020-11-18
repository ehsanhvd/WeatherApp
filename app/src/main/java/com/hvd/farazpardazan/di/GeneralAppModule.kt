package com.hvd.farazpardazan.di

import android.content.Context
import com.google.gson.Gson
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.City
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class GeneralAppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    //sample cities are hard coded, because unfortunately I couldn't find any web service for receiving city list
    //its not a good idea to provide cities list here, we should use resource and assets instead
    @Singleton
    @Provides
    fun provideCityList(@ApplicationContext context: Context) = listOf(
        City(context.getString(R.string.tehran), 35.694389, 51.421509),
        City(context.getString(R.string.shiraz), 29.6036, 52.538799),
        City(context.getString(R.string.rasht), 37.280769, 49.583191),
        City(context.getString(R.string.bandarabas), 27.186501, 56.2808),
        City(context.getString(R.string.tabriz), 38.080002, 46.291901),
        City(context.getString(R.string.esfehan), 32.657219, 51.677608)
    )
}