package com.hvd.farazpardazan.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

open class BaseDialog(context: Context) : Dialog(context){
    init {
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    }
}