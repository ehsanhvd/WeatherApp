package com.hvd.farazpardazan.util

import java.util.*

class MyCalendar : GregorianCalendar() {

    fun setToDayStart() : Long {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        return timeInMillis
    }

    fun setToDayEnd() : Long {
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
        return timeInMillis
    }

}