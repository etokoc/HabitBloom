package com.ekdev.habitapp.domain.usecase.habit_usecase

import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.model.HabitCount
import com.ekdev.habitapp.domain.model.HabitWithLogs
import com.ekdev.habitapp.domain.repository.HabitRepository
import com.ekdev.habitapp.domain.usecase.habit_log_usecase.GetAllGoalUseCase
import com.ekdev.habitapp.shared.extentions.isSameDay
import java.util.Date
import javax.inject.Inject

class GetTodayHabitCountUseCase @Inject constructor(
    private val goalUseCase: GetAllGoalUseCase,
    private val getHabitWithLogUseCase: GetHabitWithLogUseCase
) {
    suspend operator fun invoke(): HabitCount {
        val habitWithLogs = getHabitWithLogUseCase()
        val habitCount = HabitCount()
        val goals = goalUseCase().apply {
            filter { goal: Goal ->
                !goal.isCompleted
            }
        }
        val habits =
            habitWithLogs.filter { logs -> goals.find { logs.habit.goalId == it.id } != null }
        habitCount.totalCount = habits.size
        habitCount.completedHabitCount = habits.count { data -> data.isTodayCompleted == true }
        return habitCount
    }
}