package com.hvd.farazpardazan.ui.adapter

import androidx.annotation.CallSuper
import com.hvd.farazpardazan.ui.viewholder.SelectableViewHolder

abstract class SelectableAdapter<ItemViewType, ViewHolderType : SelectableViewHolder>(list: List<ItemViewType>) :
    BaseAdapter<ItemViewType, ViewHolderType>(list) {

    var selectedIndex : Int? = null

    @CallSuper
    override fun onBindViewHolder(holder: ViewHolderType, position: Int) {
        if (selectedIndex != null && selectedIndex == position){
            holder.onSelect()
        }
    }
}