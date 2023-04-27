package com.example.habit2.di

import android.content.Context
import androidx.room.Room
import com.example.habit2.database.habit.dao.HabitDao
import com.example.habit2.database.habit.HabitDatabase
import com.example.habit2.database.habit.dao.HabitWeekDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideHabitDao(habitDatabase: HabitDatabase): HabitDao
            = habitDatabase.habitDao()
    fun provideHabitWeekDataDao(habitDatabase: HabitDatabase): HabitWeekDataDao
            = habitDatabase.dataDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HabitDatabase
            = Room.databaseBuilder(
        context,
        HabitDatabase::class.java,
        "habits_db")
        .fallbackToDestructiveMigration()
        .build()
}