package com.example.habit2.database.habit.dao

import androidx.room.*
import com.example.habit2.database.habit.HabitViewModel
import com.example.habit2.database.habit.models.HabitWeekData
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate


@Dao
interface HabitWeekDataDao {


    @Query("SELECT * FROM habitData ORDER BY date DESC")
    fun getAllData(): Flow<List<HabitWeekData>>

    @Query("SELECT * FROM habitData WHERE habit_data_id = :habitId ORDER BY date DESC")
    fun getDataForHabit(habitId: Int): Flow<List<HabitWeekData>>

    @Query("SELECT * FROM habitData WHERE date = :date AND habit_data_id = :habitId")
    suspend fun getDataForDate(habitId: Int, date: LocalDate): HabitWeekData?

    @Query("SELECT * FROM habitData WHERE habit_data_id = :habitId ORDER BY date ASC LIMIT 1")
    suspend fun getOldestData(habitId: Int): HabitWeekData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(habit: HabitWeekData)

    @Delete
    suspend fun delete(habit: HabitWeekData)

}