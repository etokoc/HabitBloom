package com.ekdev.habitapp.domain.usecase.goal_usecase

import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.repository.GoalRepository
import com.ekdev.habitapp.domain.repository.HabitRepository
import jakarta.inject.Inject

class AddGoalAndHabitUseCase @Inject constructor(
    private val goalRepository: GoalRepository,
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(goal: Goal, habit: Habit) {
        val goalId = goalRepository.addGoal(goal)
        val habitWithGoalId = habit.copy(goalId = goalId.toInt())
        habitRepository.addHabit(habitWithGoalId)
    }
}
