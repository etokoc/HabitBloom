package com.ekdev.habitapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(true) val id: Long? = 0,
    val name: String,
    val description: String,
    val isCompleted: Boolean
)