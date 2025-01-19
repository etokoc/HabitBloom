package com.ekdev.habitapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "habit_logs",
    foreignKeys = [ForeignKey(
        entity = HabitEntity::class,
        parentColumns = ["id"],
        childColumns = ["habitId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("habitId")]
)
data class HabitLogEntity(
    @PrimaryKey(true) val id: Int? = 0,
    val habitId: Int? = null,
    val date: String? = null,
    val status: Boolean? = null
)