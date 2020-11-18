package com.hvd.farazpardazan.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.HourlyWeather
import kotlinx.android.synthetic.main.row_day_hour_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class HourViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //static defined calendar to prevent every-time instantiation
    companion object {
        val calendar : Calendar = Calendar.getInstance()
        val df = SimpleDateFormat("Kaa")
    }

    fun bind(weather: HourlyWeather) {
        calendar.timeInMillis = weather.timestamp

        itemView.textHour.text = df.format(calendar.time)
        itemView.textTemp.text = itemView.context.getString(R.string.celsiusDegree, weather.temp.roundToInt())
    }

}