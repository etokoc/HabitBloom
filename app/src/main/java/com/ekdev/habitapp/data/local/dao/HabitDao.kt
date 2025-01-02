package com.ekdev.habitapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ekdev.habitapp.data.entities.HabitEntity
import com.ekdev.habitapp.domain.model.HabitWithLogs

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits")
    suspend fun getAllHabits(): List<HabitEntity>

    @Query("SELECT * FROM habits WHERE id=:id")
    suspend fun getHabitById(id: Long): HabitEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity)

    @Query("SELECT * FROM habits WHERE goalId = :goalId")
    suspend fun getHabitsByGoal(goalId: Int): List<HabitEntity>

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Transaction
    @Query("SELECT * FROM habits")
    suspend fun getHabitsWithLogs(): List<HabitWithLogs>

}