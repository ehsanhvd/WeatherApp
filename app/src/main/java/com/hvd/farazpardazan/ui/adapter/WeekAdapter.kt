package com.hvd.farazpardazan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.Weather
import com.hvd.farazpardazan.ui.viewholder.WeekViewHolder

class WeekAdapter : BaseAdapter<Weather, WeekViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        return WeekViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_week_day_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {

    }
}