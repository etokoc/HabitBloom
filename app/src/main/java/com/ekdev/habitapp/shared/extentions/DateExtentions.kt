package com.ekdev.habitapp.shared.extentions

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun Date.isSameDay(date: Date): Boolean {
    val calendarFirst = Calendar.getInstance().apply { time = this@isSameDay }
    val calendarSecond = Calendar.getInstance().apply { time = date }

    return calendarFirst.get(Calendar.YEAR) == calendarSecond.get(Calendar.YEAR) &&
            calendarFirst.get(Calendar.DAY_OF_YEAR) == calendarSecond.get(Calendar.DAY_OF_YEAR)
}

fun String.toDate(): Date? {
    return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(this)
}

fun LocalDate.formatDate(pattern: String): String? {
    return this.format(DateTimeFormatter.ofPattern(pattern))
}