package com.hvd.farazpardazan.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class SelectableViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var onSelectListener: OnSelectListener? = null

    open fun selectedState(selected: Boolean) {

    }

    fun select() {
        if (onSelectListener != null) {
            onSelectListener!!.onSelected(adapterPosition)
        }
    }

    fun setOnSelectListener(onSelectListener: OnSelectListener) {
        this.onSelectListener = onSelectListener
    }

    interface OnSelectListener {
        fun onSelected(index: Int)
    }
}