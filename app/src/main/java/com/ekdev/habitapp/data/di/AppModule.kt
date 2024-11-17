package com.ekdev.habitapp.data.di

import android.content.Context
import androidx.room.Room
import com.ekdev.habitapp.data.local.HabitDao
import com.ekdev.habitapp.data.local.HabitDatabase
import com.ekdev.habitapp.data.local.HabitLocalDataSource
import com.ekdev.habitapp.data.repository.HabitRepositoryImpl
import com.ekdev.habitapp.domain.mapper.HabitMapper
import com.ekdev.habitapp.domain.repository.HabitRepository
import com.ekdev.habitapp.domain.usecase.AddHabitUseCase
import com.ekdev.habitapp.domain.usecase.DeleteHabitUseCase
import com.ekdev.habitapp.domain.usecase.GetByIdHabitUseCase
import com.ekdev.habitapp.domain.usecase.GetHabitUseCase
import com.ekdev.habitapp.domain.usecase.UpdateHabitUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHabitDatabase(context: Context): HabitDatabase {
        return Room.databaseBuilder(
            context,
            HabitDatabase::class.java,
            "habit_database"
        ).build()
    }

    @Provides
    fun providesHabitRepository(
        habitLocalDataSource: HabitLocalDataSource,
        habitMapper: HabitMapper
    ): HabitRepository {
        return HabitRepositoryImpl(habitLocalDataSource, habitMapper)
    }

    @Provides
    fun provideHabitLocalDataSource(habitDao: HabitDao): HabitLocalDataSource {
        return HabitLocalDataSource(habitDao)
    }

    @Provides
    fun provideHabitMapper(): HabitMapper {
        return HabitMapper()
    }


    @Provides
    fun provideGetHabitUseCase(habitRepository: HabitRepository): GetHabitUseCase {
        return GetHabitUseCase(habitRepository)
    }

    @Provides
    fun provideAddHabitUseCase(habitRepository: HabitRepository): AddHabitUseCase {
        return AddHabitUseCase(habitRepository)
    }

    @Provides
    fun provideDeleteHabitUseCase(habitRepository: HabitRepository): DeleteHabitUseCase {
        return DeleteHabitUseCase(habitRepository)
    }

    @Provides
    fun provideUpdateHabitUseCase(habitRepository: HabitRepository): UpdateHabitUseCase {
        return UpdateHabitUseCase(habitRepository)
    }

    @Provides
    fun provideGetByIdHabitUseCase(habitRepository: HabitRepository): GetByIdHabitUseCase {
        return GetByIdHabitUseCase(habitRepository)
    }
}