package com.hvd.farazpardazan.util

import android.content.res.Resources
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import com.hvd.farazpardazan.R

object ConditionHelper {

    @DrawableRes
    fun getIconResByCondition(@StyleRes currentTheme: Int, theme: Resources.Theme, cond: String) : Int{
        val a = theme.obtainStyledAttributes(currentTheme, intArrayOf(ConditionHelper.getConditionAttr(cond)))
        return a.getResourceId(0, 0)
    }

    @AttrRes fun getConditionAttr(cond: String) : Int {
        //icons are not mapped to states correctly
        return when (cond){
            "Thunderstorm" -> R.attr.thunder
            "Drizzle" -> R.attr.rain
            "Rain" -> R.attr.rain
            "Snow" -> R.attr.snow
            "Mist" -> R.attr.mist
            "Tornado" -> R.attr.thunder
            "Clear" -> R.attr.clearSky
            "Clouds" -> R.attr.fewClouds
            else -> R.attr.clouds
        }
    }

    @DrawableRes fun getConditionDayDrawable(cond: String) : Int {
        return when (cond){
            "Thunderstorm" -> R.drawable.ic_thunderstorm
            "Drizzle" -> R.drawable.ic_rain
            "Rain" -> R.drawable.ic_rain
            "Snow" -> R.drawable.ic_snow
            "Mist" -> R.drawable.ic_mist
            "Tornado" -> R.drawable.ic_thunderstorm
            "Clear" -> R.drawable.ic_clear_morning
            "Clouds" -> R.drawable.ic_few_cloud_morning
            else -> R.drawable.ic_few_cloud_morning
        }
    }

}