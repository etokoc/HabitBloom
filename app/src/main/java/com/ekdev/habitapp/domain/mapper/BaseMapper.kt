package com.ekdev.habitapp.domain.mapper

interface BaseMapper<T, C> {
    fun toDomain(entity: T): C
    fun toDomainList(entities: List<T>): List<C>

    fun toEntity(domain: C): T
    fun toEntityList(domains: List<C>): List<T>
}