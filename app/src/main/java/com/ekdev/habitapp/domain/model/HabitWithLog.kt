package com.ekdev.habitapp.domain.model

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import com.ekdev.habitapp.data.entities.HabitLogEntity


data class HabitWithLogs(
    @Embedded var habit: Habit,
    @Relation(
        parentColumn = "id",
        entityColumn = "habitId"
    )
    var logs: List<HabitLogEntity>,
    @Ignore
    var isTodayCompleted: Boolean? = false
) {
    constructor() : this(
        Habit(
            id = null,
            goalId = null,
            title = null,
            periodType = null
        ), emptyList(), false
    )
}
