package com.hvd.farazpardazan.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hvd.farazpardazan.R

abstract class ThemedActivity : AppCompatActivity() {

    companion object {
        var currentTheme : Int = R.style.Default
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(currentTheme)
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