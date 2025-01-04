package com.ekdev.habitapp.domain.usecase.habit_log_usecase

import com.ekdev.habitapp.data.entities.HabitLogEntity
import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.model.HabitLog
import com.ekdev.habitapp.domain.repository.GoalRepository
import com.ekdev.habitapp.domain.repository.HabitLogRepository
import com.ekdev.habitapp.shared.extentions.isSameDay
import jakarta.inject.Inject
import java.time.LocalDate
import java.util.Date

class GetHabitLogForTodayUseCase @Inject constructor(private val repository: HabitLogRepository) {
    suspend operator fun invoke(habitId: Int): HabitLog? {
        val habitLogs: List<HabitLog>? = repository.getLogsForHabit(habitId)
        return habitLogs?.find { habitLog: HabitLog ->
            habitLog.date != null && Date().isSameDay(Date(habitLog.date))
        }
    }
}
