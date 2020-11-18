package com.hvd.farazpardazan.ui.adapter

import androidx.annotation.CallSuper
import com.hvd.farazpardazan.ui.viewholder.SelectableViewHolder

abstract class SelectableAdapter<ItemViewType, ViewHolderType : SelectableViewHolder>(list: List<ItemViewType>) :
    BaseAdapter<ItemViewType, ViewHolderType>(list) {

    private var onSelected: ((SelectableAdapter<ItemViewType, ViewHolderType>, Int) -> Unit)? = null

    fun setOnSelectListener(onSelected: ((SelectableAdapter<ItemViewType, ViewHolderType>, Int) -> Unit)) {
        this.onSelected = onSelected
    }

    var selectedIndex: Int? = null
        set(value) {
            field = value
            if (onSelected != null) {
                onSelected!!(this, value ?: -1)
            }
        }

    @CallSuper
    override fun onBindViewHolder(holder: ViewHolderType, position: Int) {
        holder.selectedState(selectedIndex != null && selectedIndex == position)
    }
}