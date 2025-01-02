package com.ekdev.habitapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ekdev.habitapp.data.entities.GoalEntity

@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: GoalEntity):Long

    @Delete
    suspend fun deleteGoal(goal: GoalEntity)

    @Query("SELECT * FROM goals")
    suspend fun getAllGoals(): List<GoalEntity>

    @Query("SELECT * FROM goals where id=:id limit 1")
    suspend fun getGoalWithId(id: Int): GoalEntity?
}