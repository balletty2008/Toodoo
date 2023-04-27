package com.example.habit2.database.tracker.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.habit2.database.habit.models.Done
import com.example.habit2.database.tracker.models.Data
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface DataDao {
    @Query("SELECT * FROM data ORDER BY date DESC")
    fun getAllData(): Flow<List<Data>>

    @Query("SELECT * FROM data WHERE date = :date AND chartListId = :chartListId")
    fun getDataForDate(chartListId: Int, date: LocalDate): Data?

    @Insert
    suspend fun insert(data: Data)

    @Delete
    suspend fun delete(data: Data)
}