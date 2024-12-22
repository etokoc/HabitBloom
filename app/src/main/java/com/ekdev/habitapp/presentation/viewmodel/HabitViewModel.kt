package com.ekdev.habitapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.usecase.AddHabitUseCase
import com.ekdev.habitapp.domain.usecase.DeleteHabitUseCase
import com.ekdev.habitapp.domain.usecase.GetByIdHabitUseCase
import com.ekdev.habitapp.domain.usecase.GetHabitUseCase
import com.ekdev.habitapp.domain.usecase.UpdateHabitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val getHabitUseCase: GetHabitUseCase,
    private val addHabitUseCase: AddHabitUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val getByIdHabitUseCase: GetByIdHabitUseCase
) :
    ViewModel() {
    private val _habits = MutableLiveData<List<Habit>>()
    val habits: LiveData<List<Habit>> get() = _habits
    private val _habit = MutableLiveData<Habit?>()

    val habit: LiveData<Habit?> get() = _habit

    fun getHabits() {
        viewModelScope.launch {
            _habits.value = getHabitUseCase()
        }
    }

    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            addHabitUseCase(habit)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            deleteHabitUseCase(habit)
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            updateHabitUseCase(habit)
        }
    }

    fun getByIdHabit(id: Long) {
        viewModelScope.launch {
            _habit.value = getByIdHabitUseCase(id)
        }
    }
}
