package com.ekdev.habitapp.domain.usecase.goal_usecase

import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.repository.GoalRepository
import jakarta.inject.Inject

class GetGoalUseCase @Inject constructor(private val repository: GoalRepository) {
    suspend operator fun invoke(id: Int): Goal? {
        return repository.getGoalWithId(id)
    }
}
