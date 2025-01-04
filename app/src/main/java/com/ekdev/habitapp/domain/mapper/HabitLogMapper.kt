package com.ekdev.habitapp.domain.mapper

import com.ekdev.habitapp.data.entities.HabitLogEntity
import com.ekdev.habitapp.domain.model.HabitLog
import com.ekdev.habitapp.shared.extentions.toDate
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class HabitLogMapper @Inject constructor() : BaseMapper<HabitLogEntity, HabitLog> {
    override fun toDomain(entity: HabitLogEntity): HabitLog {
        return HabitLog(
            entity.id,
            entity.habitId,
            date = if(entity.date != null) entity.date.toString() else null,
            status = entity.status
        )
    }

    override fun toDomainList(entities: List<HabitLogEntity>): List<HabitLog> {
        return entities.map { toDomain(it) }
    }

    override fun toEntity(domain: HabitLog): HabitLogEntity {
        return HabitLogEntity(
            domain.id,
            domain.habitId,
            domain.date,
            domain.status
        )
    }

    override fun toEntityList(domains: List<HabitLog>): List<HabitLogEntity> {
        return domains.map { toEntity(it) }
    }

}