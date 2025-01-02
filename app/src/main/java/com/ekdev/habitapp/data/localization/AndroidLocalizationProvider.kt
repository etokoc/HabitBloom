package com.ekdev.habitapp.data.localization

import android.content.Context
import com.ekdev.habitapp.domain.localization.LocalizationProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class AndroidLocalizationProvider @Inject constructor(@ApplicationContext private val context: Context) :
    LocalizationProvider {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}