package com.ekdev.habitapp.domain.usecase.habit_usecase

import com.ekdev.habitapp.domain.model.HabitWithLogs
import com.ekdev.habitapp.domain.repository.HabitRepository
import com.ekdev.habitapp.shared.extentions.isSameDay
import java.util.Date
import javax.inject.Inject

class GetHabitWithLogUseCase @Inject constructor(private val repository: HabitRepository) {
    suspend operator fun invoke(): List<HabitWithLogs> {
        val habitWithLogs = repository.getHabitsWithLog()

        habitWithLogs.map { habitWithLogItem ->
            val isTodayCompleted = habitWithLogItem.logs.any { log ->
                Date().isSameDay(Date(log.date)) && log.status == true
            }
            habitWithLogItem.isTodayCompleted = isTodayCompleted
        }
        return habitWithLogs
    }
}