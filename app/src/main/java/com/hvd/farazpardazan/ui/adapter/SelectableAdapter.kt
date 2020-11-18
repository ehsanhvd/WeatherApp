package com.hvd.farazpardazan.ui.adapter

import androidx.annotation.CallSuper
import com.hvd.farazpardazan.ui.viewholder.SelectableViewHolder

abstract class SelectableAdapter<ItemViewType, ViewHolderType : SelectableViewHolder>(list: List<ItemViewType>) :
    BaseAdapter<ItemViewType, ViewHolderType>(list) {

    private var onSelectListener: OnItemSelectListener<ItemViewType, ViewHolderType>? = null

    fun setOnSelectListener(onSelectListener: OnItemSelectListener<ItemViewType, ViewHolderType>) {
        this.onSelectListener = onSelectListener
    }

    var selectedIndex: Int? = null
        set(value) {
            field = value
            if (onSelectListener != null) {
                onSelectListener!!.onSelected(this, value ?: -1)
            }
        }

    @CallSuper
    override fun onBindViewHolder(holder: ViewHolderType, position: Int) {
        holder.selectedState(selectedIndex != null && selectedIndex == position)
    }
}