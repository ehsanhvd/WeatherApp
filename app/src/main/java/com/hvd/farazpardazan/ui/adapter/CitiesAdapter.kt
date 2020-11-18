package com.hvd.farazpardazan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.City
import com.hvd.farazpardazan.ui.viewholder.CityViewHolder
import com.hvd.farazpardazan.ui.viewholder.OnViewHolderSelectListener
import com.hvd.farazpardazan.ui.viewholder.SelectableViewHolder

class CitiesAdapter(list: List<City>) : SelectableAdapter<City,CityViewHolder>(list), OnViewHolderSelectListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val citViewHolder = CityViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_city_item, parent, false)
        )
        citViewHolder.setOnSelectListener(this)
        return citViewHolder
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onSelected(selectableViewHolder: SelectableViewHolder, index: Int) {
        selectedIndex = index
        notifyDataSetChanged()
    }
}