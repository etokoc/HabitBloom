package com.ekdev.habitapp.domain.model

data class Habit(
    val id: Long?=null,
    val name: String,
    val description: String,
    val isCompleted: Boolean
)
