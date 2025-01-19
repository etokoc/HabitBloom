package com.ekdev.habitapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey(true) val id: Int? = 0,
    val name: String,
    val isCompleted: Boolean?,
    val startDate: String?=null,
    val endDate: String?=null
)