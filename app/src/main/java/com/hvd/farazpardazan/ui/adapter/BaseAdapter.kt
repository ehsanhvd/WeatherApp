package com.hvd.farazpardazan.ui.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ItemType, ViewHolderType : RecyclerView.ViewHolder> : RecyclerView.Adapter<ViewHolderType>() {

    var items = mutableListOf<ItemType>()

    fun addItems(list: List<ItemType>){
        items.addAll(list)
        notifyDataSetChanged()
    }
}