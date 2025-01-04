package com.ekdev.habitapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekdev.habitapp.domain.model.CardItem
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.model.HabitWithLogs
import com.ekdev.habitapp.domain.usecase.combined_useacase.GetCombinedCardsUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.AddHabitUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.DeleteHabitUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.GetByIdHabitUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.GetHabitUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.GetHabitWithLogUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.UpdateHabitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val getHabitUseCase: GetHabitUseCase,
    private val addHabitUseCase: AddHabitUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val getByIdHabitUseCase: GetByIdHabitUseCase,
    private val getHabitWithLogUseCase: GetHabitWithLogUseCase,
    private val getCombinedCardsUseCase: GetCombinedCardsUseCase
) :
    ViewModel() {
    private val _habits = MutableLiveData<List<Habit>>()
    private val _habitsWithLogs = MutableLiveData<List<HabitWithLogs>>()
    val habits: LiveData<List<Habit>> get() = _habits
    val habitsWithLogs: LiveData<List<HabitWithLogs>> get() = _habitsWithLogs
    private val _habit = MutableLiveData<Habit?>()
    val habit: LiveData<Habit?> get() = _habit
    private val _cardItems = MutableLiveData<List<CardItem<*>>>()
    val cardItems: LiveData<List<CardItem<*>>> get() = _cardItems

    fun getHabits() {
        viewModelScope.launch {
            _habits.value = getHabitUseCase()
        }
    }

    fun getHabitsWithLogs() {
        viewModelScope.launch {
            _habitsWithLogs.value = getHabitWithLogUseCase()
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

    fun loadCards() {
        viewModelScope.launch {
            _cardItems.postValue(getCombinedCardsUseCase())
        }
    }
}
