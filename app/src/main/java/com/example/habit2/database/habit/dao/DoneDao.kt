package com.example.habit2.database.habit.dao

import androidx.room.*
import com.example.habit2.database.habit.models.Done
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate


@Dao
interface DoneDao {
    @Query("SELECT * FROM done ORDER BY date DESC")
    fun getAllDone(): Flow<List<Done>>

    @Query("SELECT * FROM done WHERE date = :date AND habit_id = :habitId")
     fun getDoneForDate(habitId: Int, date: LocalDate): Done?

    @Query("SELECT * FROM done WHERE habit_id = :habitId ORDER BY date ASC LIMIT 1")
    suspend fun getOldestDone(habitId: Int): Done?

    @Insert
    suspend fun insert(done: Done)

    @Delete
    suspend fun delete(done: Done)

    @Query("SELECT * FROM done")
    fun getDone(): Flow<List<Done>>

    @Query("SELECT * FROM done WHERE  habit_id = :habitId")
    fun getDoneForHabit(habitId: Int): Flow<List<Done>>
}