package com.ekdev.habitapp.data.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.model.HabitLog


data class HabitWithLogs(
    @Embedded val habit: Habit,
    @Relation(
        parentColumn = "id",
        entityColumn = "habitId"
    )
    val logs: List<HabitLog>
)
