package com.example.habit2.database.habit.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.habit2.database.habit.models.HabitWithDone
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.Flow


@Dao
interface HabitWithDoneDao {

    @Transaction
    @Query("SELECT * FROM HABIT_TABLE")
    fun getHabitsWithDone(): Flow<List<HabitWithDone>>

}