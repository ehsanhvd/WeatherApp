package com.hvd.farazpardazan.ui.adapter

import com.hvd.farazpardazan.ui.viewholder.SelectableViewHolder

interface OnItemSelectListener<V, T : SelectableViewHolder> {
    fun onSelected(selectableAdapter: SelectableAdapter<V, T>, index: Int)
}