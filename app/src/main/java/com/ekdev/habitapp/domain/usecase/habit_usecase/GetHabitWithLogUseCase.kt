package com.ekdev.habitapp.domain.usecase.habit_usecase

import com.ekdev.habitapp.domain.model.HabitWithLogs
import com.ekdev.habitapp.domain.repository.HabitRepository
import com.ekdev.habitapp.shared.extentions.isSameDay
import java.util.Date
import javax.inject.Inject

class GetHabitWithLogUseCase @Inject constructor(private val repository: HabitRepository) {
    suspend operator fun invoke(): List<HabitWithLogs> {
        val habitWithLogs = repository.getHabitsWithLog()
        val today = Date()

        habitWithLogs.map { habitWithLogs ->
            val isTodayCompleted = habitWithLogs.logs.any { log ->
                today.isSameDay(Date(log.date)) && log.status == true
            }
            habitWithLogs.isTodayCompleted = isTodayCompleted
        }
        return habitWithLogs
    }
}