package com.hvd.farazpardazan.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class SelectableViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun onSelect() {

    }

}