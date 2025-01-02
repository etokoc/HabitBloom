package com.ekdev.habitapp.domain.usecase.goal_usecase

import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.repository.GoalRepository
import jakarta.inject.Inject

class GetAllGoalUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(): List<Goal> {
        return repository.getAllGoals()
    }
}
