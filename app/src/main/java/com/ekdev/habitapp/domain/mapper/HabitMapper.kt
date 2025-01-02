package com.ekdev.habitapp.domain.mapper

import com.ekdev.habitapp.data.entities.HabitEntity
import com.ekdev.habitapp.domain.model.Habit
import com.ekdev.habitapp.domain.model.PeriodType
import javax.inject.Inject

class HabitMapper @Inject constructor() : BaseMapper<HabitEntity, Habit> {
    override fun toDomain(entity: HabitEntity): Habit {
        return Habit(entity.id, entity.goalId, entity.title,
            entity.periodType?.let { PeriodType.valueOf(it) })
    }

    override fun toDomainList(entities: List<HabitEntity>): List<Habit> {
        return entities.map { toDomain(it) }
    }

    override fun toEntity(domain: Habit): HabitEntity {
        return HabitEntity(domain.id, domain.goalId, domain.title, domain.periodType?.name)
    }

    override fun toEntityList(domains: List<Habit>): List<HabitEntity> {
        return domains.map { toEntity(it) }
    }

}