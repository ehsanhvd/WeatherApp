package com.hvd.farazpardazan.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.DailyWeather
import kotlinx.android.synthetic.main.row_week_day_item.view.*
import java.util.*
import kotlin.math.roundToInt

class WeekViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //static defined calendar to prevent every-time instantiation
    companion object {
        val calendar : Calendar = Calendar.getInstance()
    }

    fun bind(dailyWeather: DailyWeather) {

        val minTemp = dailyWeather.temp.min
        val maxTemp = dailyWeather.temp.max

        itemView.textMinTemp.text = "/"+ itemView.context.getString(R.string.celsiusDegree, minTemp.roundToInt())
        itemView.textMaxTemp.text = itemView.context.getString(R.string.celsiusDegree, maxTemp.roundToInt())

        val timeStamp = dailyWeather.timeStamp
        calendar.timeInMillis = timeStamp * 1000 //convert seconds to milis

        itemView.textTitle.text = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US)
    }
}