package com.example.habit2.database.habit.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.habit2.database.habit.models.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * from habit_table")
    fun getAll(): Flow<List<Habit>>

    @Query("SELECT * from habit_table where habitId = :id")
    suspend fun getById(id: String): Habit

    @Insert
    suspend fun insert(item: Habit)

    @Update
    suspend fun update(item: Habit)

    @Delete
    suspend fun delete(item: Habit)

}