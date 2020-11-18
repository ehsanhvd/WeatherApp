package com.hvd.farazpardazan.ui.viewholder

import android.view.View
import com.hvd.farazpardazan.data.net.model.City
import kotlinx.android.synthetic.main.row_city_item.view.*

class CityViewHolder(view: View) : SelectableViewHolder(view), View.OnClickListener {

    fun bind(city: City) {
        itemView.textTitle.text = city.name
        itemView.setOnClickListener(this)
    }

    override fun selectedState(selected: Boolean) {
        itemView.imgSelection.visibility = if (selected) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    override fun onClick(v: View?) {
        select()
    }
}