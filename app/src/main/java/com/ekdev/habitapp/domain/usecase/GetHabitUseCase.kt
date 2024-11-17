package com.ekdev.habitapp.domain.usecase

import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.repository.HabitRepository
import javax.inject.Inject

class GetHabitUseCase @Inject constructor(private val repository: HabitRepository) {
    suspend operator fun invoke(): List<Habit> {
        return repository.getAllHabits()
    }
}