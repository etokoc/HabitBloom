package com.ekdev.habitapp.data.repository

import com.ekdev.habitapp.data.entities.HabitLogEntity
import com.ekdev.habitapp.data.local.datasource.HabitLocalDataSource
import com.ekdev.habitapp.data.local.datasource.HabitLogLocalDataSource
import com.ekdev.habitapp.domain.mapper.HabitLogMapper
import com.ekdev.habitapp.domain.mapper.HabitMapper
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.model.HabitLog
import com.ekdev.habitapp.domain.repository.HabitLogRepository
import com.ekdev.habitapp.domain.repository.HabitRepository
import javax.inject.Inject

class HabitLogRepositoryImpl @Inject constructor(
    private val habitLogLocalDataSource: HabitLogLocalDataSource,
    private val habitLogMapper: HabitLogMapper
) :
    HabitLogRepository {
    override suspend fun getLogForDate(habitId: Int, date: String): HabitLog? {
        return habitLogLocalDataSource.getLogForDate(habitId, date)
            ?.let { habitLogMapper.toDomain(it) }
    }

    override suspend fun addHabitLog(habitLog: HabitLog) {
        habitLogLocalDataSource.addHabitLog(habitLogEntity = habitLogMapper.toEntity(habitLog))
    }

    override suspend fun updateHabitLog(habitLog: HabitLog) {
        habitLogLocalDataSource.updateHabitLog(habitLogEntity = habitLogMapper.toEntity(habitLog))
    }

    override suspend fun getLogsForHabit(habitId: Int): List<HabitLog> {
        return habitLogMapper.toDomainList(habitLogLocalDataSource.getLogsForHabit(habitId))
    }


}