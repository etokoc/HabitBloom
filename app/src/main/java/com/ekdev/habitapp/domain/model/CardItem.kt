package com.ekdev.habitapp.domain.model

data class CardItem<T>(
    val title: String,
    val dataList: List<T>? = emptyList(),
    val cardType: EnumCardType
)