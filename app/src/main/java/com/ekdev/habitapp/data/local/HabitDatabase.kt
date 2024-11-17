package com.ekdev.habitapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ekdev.habitapp.data.entities.HabitEntity

@Database(entities = [HabitEntity::class], version = 1, exportSchema = false)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}