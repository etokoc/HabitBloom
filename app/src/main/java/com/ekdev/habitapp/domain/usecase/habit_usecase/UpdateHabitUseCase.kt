package com.ekdev.habitapp.domain.usecase.habit_usecase

import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.repository.HabitRepository
import javax.inject.Inject

class UpdateHabitUseCase @Inject constructor(private val repository: HabitRepository) {

    suspend operator fun invoke(habit: Habit) {
        repository.addHabit(habit)
    }
}