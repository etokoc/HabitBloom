package com.ekdev.habitapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekdev.habitapp.domain.model.HabitLog
import com.ekdev.habitapp.domain.usecase.habit_log_usecase.AddHabitLogUseCase
import com.ekdev.habitapp.domain.usecase.habit_log_usecase.GetLogForDateUseCase
import com.ekdev.habitapp.domain.usecase.habit_log_usecase.GetLogForHabitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitLogViewModel @Inject constructor(
    private val addHabitLogUseCase: AddHabitLogUseCase,
    private val getLogForDateUseCase: GetLogForDateUseCase,
    private val getLogForHabitUseCase: GetLogForHabitUseCase
) :
    ViewModel() {
    private val _habitLogs = MutableLiveData<List<HabitLog>>()
    val habitLogs: LiveData<List<HabitLog>> get() = _habitLogs
    private val _habitLog = MutableLiveData<HabitLog?>()

    val goal: LiveData<HabitLog?> get() = _habitLog

    fun addHabitLog(habitLog: HabitLog) {
        viewModelScope.launch {
            addHabitLogUseCase(habitLog)
        }
    }

    fun getLogForDate(id: Int, date: String) {
        viewModelScope.launch {
            _habitLog.value = getLogForDateUseCase(id, date)
        }
    }

    fun getLogForHabit(id: Int) {
        viewModelScope.launch {
            getLogForHabitUseCase(id).let {
                _habitLogs.value = it
            }
        }
    }
}
