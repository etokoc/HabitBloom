package com.ekdev.habitapp.data.local.datasource


import androidx.room.Insert
import androidx.room.Query
import com.ekdev.habitapp.data.entities.GoalEntity
import com.ekdev.habitapp.data.local.dao.GoalDao
import javax.inject.Inject

class GoalLocalDataSource @Inject constructor(
    private val goalDao: GoalDao
) {
    suspend fun insert(goal: GoalEntity): Long {
        return goalDao.insertGoal(goal)
    }

    suspend fun delete(goal: GoalEntity) {
        return goalDao.deleteGoal(goal)
    }

    suspend fun getAllGoals(): List<GoalEntity> {
        return goalDao.getAllGoals()
    }

    suspend fun getGoalWithId(id: Int): GoalEntity? {
        return goalDao.getGoalWithId(id)
    }

}
