package com.example.habit2.database.tracker.dao

import androidx.room.*
import com.example.habit2.database.habit.models.Habit
import com.example.habit2.database.tracker.models.Chart
import kotlinx.coroutines.flow.Flow


@Dao
interface ChartDao {

    @Query("SELECT * from chart_table")
    fun getAll(): Flow<List<Chart>>

    @Query("SELECT * from chart_table where chart_id = :id")
    suspend fun getById(id: Long): Chart

    @Insert
    suspend fun insert(item: Chart)

    @Update
    suspend fun update(item: Chart)

    @Delete
    suspend fun delete(item: Chart)

}