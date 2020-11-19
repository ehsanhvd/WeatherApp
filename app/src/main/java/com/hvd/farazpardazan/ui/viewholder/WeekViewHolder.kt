package com.hvd.farazpardazan.ui.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.DailyWeather
import com.hvd.farazpardazan.util.ConditionHelper
import kotlinx.android.synthetic.main.row_week_day_item.view.*
import java.util.*
import kotlin.math.roundToInt

class WeekViewHolder(view: View) : SelectableViewHolder(view), View.OnClickListener {

    //static defined calendar to prevent every-time instantiation
    companion object {
        val calendar: Calendar = Calendar.getInstance()
    }

    fun bind(dailyWeather: DailyWeather) {

        val minTemp = dailyWeather.temp.min
        val maxTemp = dailyWeather.temp.max
        val timeStamp = dailyWeather.timeStamp
        val weatherCond = dailyWeather.weather[0].main

        itemView.textMinTemp.text =
            "/" + itemView.context.getString(R.string.celsiusDegree, minTemp.roundToInt())
        itemView.textMaxTemp.text =
            itemView.context.getString(R.string.celsiusDegree, maxTemp.roundToInt())

        calendar.timeInMillis = timeStamp * 1000 //convert seconds to milis

        itemView.textTitle.text =
            calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)

        val drawableRes = ConditionHelper.getConditionDayDrawable(weatherCond)
        Glide
            .with(itemView.context)
            .load(drawableRes)
            .centerInside()
            .into(itemView.imgIcon)

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