package com.ekdev.habitapp.domain.usecase.combined_useacase

import com.ekdev.habitapp.R
import com.ekdev.habitapp.data.localization.AndroidLocalizationProvider
import com.ekdev.habitapp.domain.model.CardItem
import com.ekdev.habitapp.domain.model.EnumCardType
import com.ekdev.habitapp.domain.repository.GoalRepository
import com.ekdev.habitapp.domain.repository.HabitRepository
import jakarta.inject.Inject

class GetCombinedCardsUseCase @Inject constructor(
    private val habitRepository: HabitRepository,
    private val goalRepository: GoalRepository,
    private val localizationProvider: AndroidLocalizationProvider
) {

    suspend operator fun invoke(): List<CardItem<*>> {
        val habitsWithLogs = habitRepository.getHabitsWithLog()
        val goals = goalRepository.getAllGoals()

        val cardList = mutableListOf<CardItem<*>>()
        cardList.add(
            CardItem(
                localizationProvider.getString(R.string.today_habit),
                habitsWithLogs,
                EnumCardType.TODAY_HABIT_CARD
            )
        )

        cardList.add(
            CardItem(
                localizationProvider.getString(R.string.your_goals),
                goals,
                EnumCardType.YOUR_GOALS_CARD
            )
        )

        return cardList
    }
}
