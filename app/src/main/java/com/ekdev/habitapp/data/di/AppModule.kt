package com.ekdev.habitapp.data.di

import android.content.Context
import androidx.room.Room
import com.ekdev.habitapp.data.local.dao.HabitDao
import com.ekdev.habitapp.data.local.HabitDatabase
import com.ekdev.habitapp.data.local.dao.GoalDao
import com.ekdev.habitapp.data.local.dao.HabitLogDao
import com.ekdev.habitapp.data.local.datasource.GoalLocalDataSource
import com.ekdev.habitapp.data.local.datasource.HabitLocalDataSource
import com.ekdev.habitapp.data.local.datasource.HabitLogLocalDataSource
import com.ekdev.habitapp.data.repository.GoalRepositoryImpl
import com.ekdev.habitapp.data.repository.HabitLogRepositoryImpl
import com.ekdev.habitapp.data.repository.HabitRepositoryImpl
import com.ekdev.habitapp.domain.mapper.GoalMapper
import com.ekdev.habitapp.domain.mapper.HabitLogMapper
import com.ekdev.habitapp.domain.mapper.HabitMapper
import com.ekdev.habitapp.domain.repository.GoalRepository
import com.ekdev.habitapp.domain.repository.HabitLogRepository
import com.ekdev.habitapp.domain.repository.HabitRepository
import com.ekdev.habitapp.domain.usecase.habit_usecase.AddHabitUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.DeleteHabitUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.GetByIdHabitUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.GetHabitUseCase
import com.ekdev.habitapp.domain.usecase.habit_usecase.UpdateHabitUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHabitDatabase(@ApplicationContext context: Context): HabitDatabase {
        return Room.databaseBuilder(
            context,
            HabitDatabase::class.java,
            "habit_database"
        ).build()
    }

    @Provides
    fun provideHabitDao(habitDatabase: HabitDatabase): HabitDao {
        return habitDatabase.habitDao()
    }

    @Provides
    fun provideGoalDao(habitDatabase: HabitDatabase): GoalDao {
        return habitDatabase.goalDao()
    }

    @Provides
    fun provideHabitLogDao(habitDatabase: HabitDatabase): HabitLogDao {
        return habitDatabase.habitLogDao()
    }


    @Provides
    fun providesHabitRepository(
        habitLocalDataSource: HabitLocalDataSource,
        habitMapper: HabitMapper
    ): HabitRepository {
        return HabitRepositoryImpl(habitLocalDataSource, habitMapper)
    }

    @Provides
    fun providesHabitLogRepository(
        habitLogLocalDataSource: HabitLogLocalDataSource,
        habitLogMapper: HabitLogMapper
    ): HabitLogRepository {
        return HabitLogRepositoryImpl(habitLogLocalDataSource, habitLogMapper)
    }

    @Provides
    fun providesGoalRepository(
        goalLocalDataSource: GoalLocalDataSource,
        goalMapper: GoalMapper
    ): GoalRepository {
        return GoalRepositoryImpl(goalLocalDataSource, goalMapper)
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