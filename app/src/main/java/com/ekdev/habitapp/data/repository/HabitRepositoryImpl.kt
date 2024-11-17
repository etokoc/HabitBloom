package com.ekdev.habitapp.data.repository

import com.ekdev.habitapp.data.local.HabitLocalDataSource
import com.ekdev.habitapp.domain.mapper.HabitMapper
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.repository.HabitRepository
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val habitLocalDataSource: HabitLocalDataSource,
    private val habitMapper: HabitMapper
) :
    HabitRepository {
    override suspend fun getAllHabits(): List<Habit> {
        return habitMapper.toDomainList(habitLocalDataSource.getAllHabits())
    }


    override suspend fun addHabit(habit: Habit) {
        habitLocalDataSource.addHabit(habitEntity = habitMapper.toEntity(habit))
    }


    override suspend fun deleteHabit(habit: Habit) {
        habitLocalDataSource.deleteHabit(habitEntity = habitMapper.toEntity(habit))
    }

    override suspend fun getByIdHabit(id: Long): Habit? {
        return habitLocalDataSource.getHabitById(id)?.let { habitMapper.toDomain(it) }
    }
}