package com.example.habit2.di

import android.content.Context
import androidx.room.Room
import com.example.habit2.database.habit.dao.HabitDao
import com.example.habit2.database.habit.HabitDatabase
import com.example.habit2.database.habit.dao.DoneDao
import com.example.habit2.database.habit.dao.HabitWithDoneDao
import com.example.habit2.database.tracker.ChartDatabase
import com.example.habit2.database.tracker.dao.ChartDao
import com.example.habit2.database.tracker.dao.ChartWithDataDao
import com.example.habit2.database.tracker.dao.DataDao
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
    @Singleton
    @Provides
    fun provideDoneDao(habitDatabase: HabitDatabase): DoneDao
            = habitDatabase.doneDao()

    @Singleton
    @Provides
    fun provideHabitWithDoneDao(habitDatabase: HabitDatabase): HabitWithDoneDao
            = habitDatabase.habitWithDoneDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HabitDatabase
            = Room.databaseBuilder(
        context,
        HabitDatabase::class.java,
        "habits_db")
        .fallbackToDestructiveMigration()
        .build()


    @Singleton
    @Provides
    fun provideChartDao(chartDatabase: ChartDatabase): ChartDao
            = chartDatabase.chartDao()
    @Singleton
    @Provides
    fun provideDataDao(chartDatabase: ChartDatabase): DataDao
            = chartDatabase.dataDao()

    @Singleton
    @Provides
    fun provideChartWithDataDao(chartDatabase: ChartDatabase): ChartWithDataDao
            = chartDatabase.chartWithDataDao()

    @Singleton
    @Provides
    fun provideChartDatabase(@ApplicationContext context: Context): ChartDatabase
            = Room.databaseBuilder(
        context,
        ChartDatabase::class.java,
        "charts_db")
        .fallbackToDestructiveMigration()
        .build()
}