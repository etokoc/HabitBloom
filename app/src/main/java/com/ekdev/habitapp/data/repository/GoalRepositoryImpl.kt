package com.ekdev.habitapp.data.repository

import com.ekdev.habitapp.data.local.datasource.GoalLocalDataSource
import com.ekdev.habitapp.domain.mapper.GoalMapper
import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.repository.GoalRepository
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(
    private val goalLocalDataSource: GoalLocalDataSource,
    private val goalMapper: GoalMapper
) :
    GoalRepository {
    override suspend fun getAllGoals(): List<Goal> {
        return goalMapper.toDomainList(goalLocalDataSource.getAllGoals())
    }

    override suspend fun getGoalWithId(id: Int): Goal? {
        return goalLocalDataSource.getGoalWithId(id)?.let { goalMapper.toDomain(it) }
    }

    override suspend fun addGoal(goal: Goal):Long {
        return goalLocalDataSource.insert(goalMapper.toEntity(goal))
    }

    override suspend fun deleteGoal(goal: Goal) {
        goalLocalDataSource.delete(goalMapper.toEntity(goal))
    }

}