package com.ekdev.habitapp.domain.model

data class HabitLog(
    val id:Int,
    val habitId: Int,
    val date: String,
    val status: Boolean
)
