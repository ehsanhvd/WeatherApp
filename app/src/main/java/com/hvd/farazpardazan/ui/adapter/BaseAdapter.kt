package com.hvd.farazpardazan.ui.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ItemType, ViewHolderType : RecyclerView.ViewHolder>(items: List<ItemType>) : RecyclerView.Adapter<ViewHolderType>() {

    var mItems = mutableListOf<ItemType>()

    init {
        mItems.addAll(items)
    }

    fun addItems(list: List<ItemType>){
        mItems.addAll(list)
        notifyDataSetChanged()
    }
}