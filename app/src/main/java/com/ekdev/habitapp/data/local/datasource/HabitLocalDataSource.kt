package com.ekdev.habitapp.data.local.datasource


import com.ekdev.habitapp.data.entities.HabitEntity
import com.ekdev.habitapp.data.local.dao.HabitDao
import com.ekdev.habitapp.domain.model.HabitWithLogs
import javax.inject.Inject

class HabitLocalDataSource @Inject constructor(
    private val habitDao: HabitDao
) {

    suspend fun getAllHabits(): List<HabitEntity> {
        return habitDao.getAllHabits()
    }

    suspend fun getHabitById(id: Long): HabitEntity? {
        return habitDao.getHabitById(id)
    }

    suspend fun addHabit(habitEntity: HabitEntity) {
        habitDao.insertHabit(habitEntity)
    }

    suspend fun updateHabit(habitEntity: HabitEntity) {
        habitDao.insertHabit(habitEntity)
    }

    suspend fun deleteHabit(habitEntity: HabitEntity) {
        habitDao.deleteHabit(habitEntity)
    }

    suspend fun getHabitsWithLog(): List<HabitWithLogs> {
       return habitDao.getHabitsWithLogs()
    }
}
