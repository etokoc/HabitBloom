package com.ekdev.habitapp.domain.localization

interface LocalizationProvider {
    fun getString(resId: Int): String
}