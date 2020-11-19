package com.hvd.farazpardazan.ui.dialog

import android.content.Context
import com.hvd.farazpardazan.R
import kotlinx.android.synthetic.main.network_error_dialog.*

class NetworkErrorDialog(context: Context, val onRetry: ((NetworkErrorDialog) -> Unit)) :
    BaseDialog(context) {
    init {
        setContentView(R.layout.network_error_dialog)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        textRetry.setOnClickListener {
            onRetry(this)
        }
    }
}