package com.ekdev.habitapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekdev.habitapp.domain.model.HabitLog
import com.ekdev.habitapp.domain.usecase.habit_log_usecase.AddHabitLogUseCase
import com.ekdev.habitapp.domain.usecase.habit_log_usecase.GetHabitLogForTodayUseCase
import com.ekdev.habitapp.domain.usecase.habit_log_usecase.GetLogForDateUseCase
import com.ekdev.habitapp.domain.usecase.habit_log_usecase.GetLogForHabitUseCase
import com.ekdev.habitapp.domain.usecase.habit_log_usecase.UpdateHabitLogUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.GetByIdHabitUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.GetHabitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitLogViewModel @Inject constructor(
    private val addHabitLogUseCase: AddHabitLogUseCase,
    private val getLogForDateUseCase: GetLogForDateUseCase,
    private val getLogForHabitUseCase: GetLogForHabitUseCase,
    private val getByIdHabitUseCase: GetByIdHabitUseCase,
    private val updateHabitLogUseCase: UpdateHabitLogUseCase,
    private val getHabitLogForTodayUseCase: GetHabitLogForTodayUseCase
) :
    ViewModel() {
    private val _habitLogs = MutableLiveData<List<HabitLog>>()
    val habitLogs: LiveData<List<HabitLog>> get() = _habitLogs
    private val _habitLog = MutableLiveData<HabitLog?>()

    val goal: LiveData<HabitLog?> get() = _habitLog

    fun addHabitLog(habitId: Long?) {
        viewModelScope.launch {
            val habitLog = HabitLog(habitId = habitId?.toInt())
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

    private fun updateHabitLog(habitLog: HabitLog) {
        viewModelScope.launch {
            updateHabitLogUseCase(habitLog)
        }
    }

    //Add or negation status of habit log
    suspend fun addOrUpdateHabitLog(habitId: Long, checked: Boolean) {
        getHabitLogForTodayUseCase(habitId = habitId.toInt())?.let {
            updateHabitLog(it.copy(status = checked))
        } ?: run {
            addHabitLog(habitId)
        }
    }
}
