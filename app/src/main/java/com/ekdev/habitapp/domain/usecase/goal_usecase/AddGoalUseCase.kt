package com.ekdev.habitapp.domain.usecase.goal_usecase

import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.repository.GoalRepository
import jakarta.inject.Inject

class AddGoalUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(goal: Goal): Long {
      return  repository.addGoal(goal)
    }
}
