package com.ekdev.habitapp.domain.model

data class Goal(
    val id: Int? = null,
    val name: String,
    val isCompleted: Boolean,
    val startDate: String? = null,
    val endDate: String? = null
)
