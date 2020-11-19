package com.hvd.farazpardazan.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

object CommonUtils {

    fun openUrl(context: Context, url: String?): Boolean {
        return if (url == null || url.isEmpty()) {
            false
        } else try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
            true
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
            false
        }
    }

}