package com.hvd.farazpardazan.ui.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.hvd.farazpardazan.App
import com.hvd.farazpardazan.R

class CustomTextView : AppCompatTextView {

    constructor(context: Context) : super(context){
        init(context, null, 0)
    }

    constructor(context: Context, attrs : AttributeSet) : super(context,attrs){
        init(context, attrs, 0)
    }

    constructor(context: Context,  attrs: AttributeSet , defStyleAttr : Int) : super(context, attrs, defStyleAttr){
        init(context, attrs, defStyleAttr)
    }

    fun init(context: Context,  attrs: AttributeSet? , defStyleAttr : Int) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView, defStyleAttr, 0)
        val fontI = ta.getInt(R.styleable.CustomTextView_fontIndex, 0)

        typeface = App.typefaces[fontI]
        ta.recycle()
    }
}