package com.ekdev.habitapp.data.local.datasource


import com.ekdev.habitapp.data.entities.HabitLogEntity
import com.ekdev.habitapp.data.local.dao.HabitLogDao
import javax.inject.Inject

class HabitLogLocalDataSource @Inject constructor(
    private val habitLogDao: HabitLogDao
) {

    suspend fun getLogForDate(id: Int, date: String): HabitLogEntity? {
        return habitLogDao.getLogForDate(id, date)
    }

    suspend fun getLogsForHabit(id: Int): List<HabitLogEntity> {
        return habitLogDao.getLogsForHabit(id);
    }

    suspend fun updateHabitLog(habitLogEntity: HabitLogEntity) {
        habitLogDao.update(habitLogEntity)
    }
   suspend fun addHabitLog(habitLogEntity: HabitLogEntity) {
        habitLogDao.insert(habitLogEntity)
    }
}
