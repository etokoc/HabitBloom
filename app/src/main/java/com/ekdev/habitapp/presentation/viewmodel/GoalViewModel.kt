package com.ekdev.habitapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.model.PeriodType
import com.ekdev.habitapp.domain.usecase.goal_usecase.AddGoalUseCase
import com.ekdev.habitapp.domain.usecase.goal_usecase.DeleteGoalUseCase
import com.ekdev.habitapp.domain.usecase.goal_usecase.GetAllGoalUseCase
import com.ekdev.habitapp.domain.usecase.goal_usecase.GetGoalUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.AddHabitUseCase
import com.ekdev.habitapp.presentation.ui.custom_views.StepProgressView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val getAllGoalUseCase: GetAllGoalUseCase,
    private val addGoalUseCase: AddGoalUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase,
    private val getGoalUseCase: GetGoalUseCase,
    private val addHabitUseCase: AddHabitUseCase
) :
    ViewModel() {
    private val _goals = MutableLiveData<List<Goal>>()
    val goals: LiveData<List<Goal>> get() = _goals
    private val _goal = MutableLiveData<Goal?>()

    val goal: LiveData<Goal?> get() = _goal

    fun getGoalWithId(id: Int) {
        viewModelScope.launch {
            _goal.value = getGoalUseCase(id)
        }
    }

    fun getGoals() {
        viewModelScope.launch {
            _goals.value = getAllGoalUseCase()
        }
    }

    fun addGoal(goal: Goal) {
        viewModelScope.launch {
            addGoalUseCase(goal)
        }
    }

    fun addGoalAndHabitUseCase(
        goalName: String,
        habitTitle: String,
        habitPeriodType: StepProgressView.StepEnum,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val goalID = addGoalUseCase(Goal(name = goalName, isCompleted = false))
                addHabitUseCase(
                    Habit(
                        goalId = goalID.toInt(),
                        title = habitTitle,
                        periodType = when (habitPeriodType) {
                            StepProgressView.StepEnum.STEP_21_DAYS -> PeriodType.STEP_21_DAYS
                            StepProgressView.StepEnum.STEP_1_MONTH -> PeriodType.STEP_1_MONTH
                            StepProgressView.StepEnum.STEP_3_MONTH -> PeriodType.STEP_3_MONTH
                        }
                    )
                )
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun deleteGoal(goal: Goal) {
        viewModelScope.launch {
            deleteGoalUseCase(goal)
        }
    }
}
