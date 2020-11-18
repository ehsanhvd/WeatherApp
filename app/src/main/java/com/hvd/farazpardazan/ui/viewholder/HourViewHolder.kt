package com.hvd.farazpardazan.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.HourlyWeather
import com.hvd.farazpardazan.util.ConditionHelper
import kotlinx.android.synthetic.main.row_day_hour_item.view.*
import java.util.*
import kotlin.math.roundToInt

class HourViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //static defined calendar to prevent every-time instantiation
    companion object {
        val calendar : Calendar = Calendar.getInstance()
    }

    fun bind(hourlyWeather: HourlyWeather) {

        val isAfterMidday = calendar.get(Calendar.AM_PM) == 0
        var hour = calendar.get(Calendar.HOUR)

        calendar.timeInMillis = hourlyWeather.timestamp * 1000
        itemView.textTemp.text = itemView.context.getString(R.string.celsiusDegree, hourlyWeather.temp.roundToInt())
        itemView.imgIcon.setImageResource(ConditionHelper.getConditionDayDrawable(hourlyWeather.weather[0].main))

        if (isAfterMidday){
            itemView.textHour.text = itemView.context.getString(R.string.amPlaceholder, hour)
        } else {
            if (hour == 0){
                hour = 12
            }
            itemView.textHour.text = itemView.context.getString(R.string.pmPlaceholder, hour)
        }
    }

}