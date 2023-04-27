package com.example.habit2.database.habit.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.habit2.database.habit.models.HabitWithData
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitWithDataDao {

    @Transaction
    @Query("SELECT * FROM habit_table")
    fun getHabitsWithEntries(): Flow<List<HabitWithData>>

}