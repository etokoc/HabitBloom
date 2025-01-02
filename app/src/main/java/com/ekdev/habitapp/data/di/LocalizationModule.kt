package com.ekdev.habitapp.data.di

import android.content.Context
import com.ekdev.habitapp.data.localization.AndroidLocalizationProvider
import com.ekdev.habitapp.domain.localization.LocalizationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalizationModule {
    @Provides
    @Singleton
    fun provideLocalizationProvider(context: Context): LocalizationProvider {
        return AndroidLocalizationProvider(context)
    }
}