package com.hvd.farazpardazan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.HourlyWeather
import com.hvd.farazpardazan.ui.viewholder.HourViewHolder

class DayAdapter(list: List<HourlyWeather>) : BaseAdapter<HourlyWeather, HourViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        return HourViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_day_hour_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        holder.bind(mItems[position])
    }
}