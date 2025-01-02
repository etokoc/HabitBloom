package com.ekdev.habitapp.domain.repository

import com.ekdev.habitapp.domain.model.Goal

interface GoalRepository {
    suspend fun getAllGoals(): List<Goal>
    suspend fun getGoalWithId(id: Int): Goal?
    suspend fun addGoal(goal: Goal):Long
    suspend fun deleteGoal(goal: Goal)
}