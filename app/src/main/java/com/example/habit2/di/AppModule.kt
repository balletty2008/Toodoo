package com.example.habit2.di

import android.content.Context
import androidx.room.Room
import com.example.habit2.database.HabitDao
import com.example.habit2.database.HabitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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