package com.hvd.farazpardazan.data

import android.content.Context
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.City
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CitiesProvider @Inject constructor(@ApplicationContext val context: Context) {

    //sample cities are hard coded, because unfortunately I couldn't find any web service for receiving city list
    fun getCities() : List<City> = listOf(
        City(context.getString(R.string.tehran), 35.694389,51.421509),
        City(context.getString(R.string.shiraz), 29.6036,52.538799),
        City(context.getString(R.string.rasht), 37.280769,49.583191),
        City(context.getString(R.string.bandarabas), 27.186501,56.2808),
        City(context.getString(R.string.tabriz), 38.080002,46.291901),
        City(context.getString(R.string.esfehan), 32.657219,51.677608)
    )
}