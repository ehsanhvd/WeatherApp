package com.hvd.farazpardazan.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class GeneralAppModule {

    @Singleton
    @Provides
    fun provideGson() : Gson = Gson()
}