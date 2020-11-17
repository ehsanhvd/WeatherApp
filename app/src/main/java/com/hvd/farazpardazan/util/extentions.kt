package com.hvd.farazpardazan.util

import android.content.res.Resources
import androidx.annotation.DrawableRes
import com.hvd.farazpardazan.R


@DrawableRes
fun Resources.Theme.getIconResByCondition(cond: String) : Int{
    val a = obtainStyledAttributes(R.style.AppTheme, intArrayOf(R.attr.clearSky))
    return a.getResourceId(0, 0)
}