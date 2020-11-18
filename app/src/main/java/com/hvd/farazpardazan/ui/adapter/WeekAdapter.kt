package com.hvd.farazpardazan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.DailyWeather
import com.hvd.farazpardazan.ui.viewholder.WeekViewHolder

class WeekAdapter(list: List<DailyWeather>) : BaseAdapter<DailyWeather, WeekViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        return WeekViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_week_day_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        holder.bind(mItems[position])
    }
}