package com.ekdev.habitapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ekdev.habitapp.data.entities.HabitLogEntity

@Dao
interface HabitLogDao {
    @Insert
    suspend fun insert(habitLog: HabitLogEntity)

    @Query("SELECT * FROM habit_logs WHERE habitId = :habitId AND date = :date")
    suspend fun getLogForDate(habitId: Int, date: String): HabitLogEntity?

    @Query("SELECT * FROM habit_logs WHERE habitId = :habitId")
    suspend fun getLogsForHabit(habitId: Int): List<HabitLogEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(habitLog: HabitLogEntity)

}