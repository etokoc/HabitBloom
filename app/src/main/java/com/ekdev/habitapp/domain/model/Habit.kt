package com.ekdev.habitapp.domain.model

data class Habit(
    val id: Long? = null,
    val goalId: Int? = null,
    val title: String? = null,
    val periodType: PeriodType? = null
)
