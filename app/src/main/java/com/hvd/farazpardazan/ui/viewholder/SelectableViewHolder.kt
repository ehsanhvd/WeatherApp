package com.hvd.farazpardazan.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class SelectableViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var onSelectListener: OnViewHolderSelectListener? = null

    open fun selectedState(selected: Boolean) {

    }

    fun select() {
        if (onSelectListener != null) {
            onSelectListener!!.onSelected(this, adapterPosition)
        }
    }

    fun setOnSelectListener(onSelectListener: OnViewHolderSelectListener) {
        this.onSelectListener = onSelectListener
    }

}