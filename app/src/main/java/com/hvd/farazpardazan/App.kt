package com.hvd.farazpardazan

import android.graphics.Typeface
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication() {
    companion object {
        val typefaces = arrayListOf<Typeface>()
    }

    override fun onCreate() {
        super.onCreate()
        typefaces.add(Typeface.createFromAsset(assets,("fonts/Montserrat-Regular.ttf")))
        typefaces.add(Typeface.createFromAsset(assets,("fonts/Montserrat-Light.ttf")))
        typefaces.add(Typeface.createFromAsset(assets,("fonts/Montserrat-Black.ttf")))
        typefaces.add(Typeface.createFromAsset(assets,("fonts/Montserrat-Medium.ttf")))
        typefaces.add(Typeface.createFromAsset(assets,("fonts/Montserrat-Italic.ttf")))
    }
}