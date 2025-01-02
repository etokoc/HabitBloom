package com.ekdev.habitapp.domain.usecase.habit_log_usecase

import com.ekdev.habitapp.data.entities.HabitLogEntity
import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.model.HabitLog
import com.ekdev.habitapp.domain.repository.GoalRepository
import com.ekdev.habitapp.domain.repository.HabitLogRepository
import jakarta.inject.Inject

class GetLogForHabitUseCase @Inject constructor(private val repository: HabitLogRepository) {
    suspend operator fun invoke(habitId: Int): List<HabitLog>? {
        return repository.getLogsForHabit(habitId)
    }
}
