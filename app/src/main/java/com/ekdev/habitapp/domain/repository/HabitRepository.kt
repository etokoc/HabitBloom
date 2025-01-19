package com.ekdev.habitapp.domain.repository

import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.model.HabitWithLogs

interface HabitRepository {
    suspend fun getAllHabits(): List<Habit>
    suspend fun addHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    suspend fun getByIdHabit(id: Long): Habit?
    suspend fun getHabitsWithLog(): List<HabitWithLogs>
}