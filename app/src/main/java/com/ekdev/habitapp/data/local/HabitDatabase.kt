package com.ekdev.habitapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ekdev.habitapp.data.entities.HabitEntity
import com.ekdev.habitapp.data.entities.HabitLogEntity
import com.ekdev.habitapp.data.entities.GoalEntity
import com.ekdev.habitapp.data.local.dao.GoalDao
import com.ekdev.habitapp.data.local.dao.HabitDao
import com.ekdev.habitapp.data.local.dao.HabitLogDao

@Database(
    entities = [HabitEntity::class, GoalEntity::class, HabitLogEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun goalDao(): GoalDao
    abstract fun habitLogDao(): HabitLogDao
}