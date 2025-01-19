package com.ekdev.habitapp.domain.repository

import com.ekdev.habitapp.domain.model.HabitLog

interface HabitLogRepository {
    suspend fun getLogForDate(habitId: Int, date: String): HabitLog?
    suspend fun addHabitLog(habitLog: HabitLog)
    suspend fun updateHabitLog(habitLog: HabitLog)
    suspend fun getLogsForHabit(habitId: Int): List<HabitLog>?
}