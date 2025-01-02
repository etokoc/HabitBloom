package com.ekdev.habitapp.domain.usecase.goal_usecase

import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.repository.GoalRepository
import jakarta.inject.Inject

class DeleteGoalUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(goal: Goal) {
        repository.deleteGoal(goal)
    }
}
