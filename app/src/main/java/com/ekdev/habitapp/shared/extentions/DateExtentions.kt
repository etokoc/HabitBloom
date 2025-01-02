package com.ekdev.habitapp.shared.extentions

import java.util.Calendar
import java.util.Date


fun Date.isSameDay(date: Date): Boolean {
    val calendarFirst = Calendar.getInstance().apply { time = this@isSameDay }
    val calendarSecond = Calendar.getInstance().apply { time = date }

    return calendarFirst.get(Calendar.YEAR) == calendarSecond.get(Calendar.YEAR) &&
            calendarFirst.get(Calendar.DAY_OF_YEAR) == calendarSecond.get(Calendar.DAY_OF_YEAR)
}