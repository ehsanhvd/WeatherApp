package com.hvd.farazpardazan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.DailyWeather
import com.hvd.farazpardazan.ui.viewholder.OnViewHolderSelectListener
import com.hvd.farazpardazan.ui.viewholder.SelectableViewHolder
import com.hvd.farazpardazan.ui.viewholder.WeekViewHolder

class WeekAdapter(list: List<DailyWeather>) : SelectableAdapter<DailyWeather, WeekViewHolder>(list),
    OnViewHolderSelectListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val weekVH =  WeekViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_week_day_item, parent, false)
        )

        weekVH.setOnSelectListener(this)
        return weekVH
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(mItems[position])
    }

    override fun onSelected(selectableViewHolder: SelectableViewHolder, index: Int) {
        selectedIndex = index
        notifyDataSetChanged()
    }

}