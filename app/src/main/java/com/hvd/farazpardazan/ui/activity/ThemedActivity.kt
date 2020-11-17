package com.hvd.farazpardazan.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class ThemedActivity : AppCompatActivity() {

    companion object {
        var currentTheme : Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (currentTheme != 0){
            setTheme(currentTheme)
        }
        super.onCreate(savedInstanceState)
    }

    fun setThemeAndRefresh(resId: Int) {
        if (resId != currentTheme){
            currentTheme = resId
            super.setTheme(resId)
            recreate()
        }
    }
}