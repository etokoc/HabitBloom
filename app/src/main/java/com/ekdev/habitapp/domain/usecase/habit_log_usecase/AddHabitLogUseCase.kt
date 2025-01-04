package com.ekdev.habitapp.domain.usecase.habit_log_usecase

import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.model.HabitLog
import com.ekdev.habitapp.domain.repository.GoalRepository
import com.ekdev.habitapp.domain.repository.HabitLogRepository
import com.ekdev.habitapp.shared.extentions.formatDate
import jakarta.inject.Inject
import java.time.LocalDate

class AddHabitLogUseCase @Inject constructor(private val repository: HabitLogRepository) {
    suspend operator fun invoke(habitLog: HabitLog) {
        val habitLogData = habitLog.copy(date = LocalDate.now().formatDate("dd-MM-yyyy"))
        repository.addHabitLog(habitLogData)
    }
}
