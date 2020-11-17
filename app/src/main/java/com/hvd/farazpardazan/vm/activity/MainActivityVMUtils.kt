package com.hvd.farazpardazan.vm.activity

import com.hvd.farazpardazan.ui.state.DayState
import java.util.*

object MainActivityVMUtils {

    fun getDayState() : DayState {
        val cal = GregorianCalendar.getInstance()
        val hour = cal.get(Calendar.HOUR_OF_DAY)

        when (hour) {
            in 0..6 -> {
                return DayState.DAWN
            }
            in 7..11 -> {
                return DayState.MORNING
            }
            in 12..16 -> {
                return DayState.NOON
            }
            in 17..19 -> {
                return DayState.EVENING
            }
            in 20..23 -> {
                return DayState.NIGHT
            }
            else -> throw IllegalStateException("Invalid day state, hour is $hour")
        }

    }
}