package com.ekdev.habitapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "habits",
    foreignKeys = [ForeignKey(
        entity = GoalEntity::class,
        parentColumns = ["id"],
        childColumns = ["goalId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("goalId")]
)
data class HabitEntity(
    @PrimaryKey(true) val id: Long? = 0,
    val goalId: Int?,
    val title: String? = null,
    val periodType: String? = null
)