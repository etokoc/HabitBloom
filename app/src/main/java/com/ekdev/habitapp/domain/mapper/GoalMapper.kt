package com.ekdev.habitapp.domain.mapper

import com.ekdev.habitapp.data.entities.GoalEntity
import com.ekdev.habitapp.data.entities.HabitEntity
import com.ekdev.habitapp.domain.model.Goal
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.model.PeriodType
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class GoalMapper @Inject constructor() : BaseMapper<GoalEntity, Goal> {
    override fun toDomain(entity: GoalEntity): Goal {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val startDate =
            if (!entity.startDate.isNullOrEmpty()) dateFormat.format(entity.startDate) else null
        val endDate =
            if (!entity.endDate.isNullOrEmpty()) dateFormat.format(entity.endDate) else null
        return Goal(
            entity.id,
            entity.name,
            entity.isCompleted ?: false,
            startDate = startDate,
            endDate = endDate
        )
    }

    override fun toDomainList(entities: List<GoalEntity>): List<Goal> {
        return entities.map { toDomain(it) }
    }

    override fun toEntity(domain: Goal): GoalEntity {
        return GoalEntity(
            domain.id,
            domain.name,
            domain.isCompleted,
            domain.startDate,
            domain.endDate
        )
    }

    override fun toEntityList(domains: List<Goal>): List<GoalEntity> {
        return domains.map { toEntity(it) }
    }

}