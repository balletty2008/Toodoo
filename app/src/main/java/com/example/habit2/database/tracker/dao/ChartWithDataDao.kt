package com.example.habit2.database.tracker.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.habit2.database.tracker.models.ChartWithData
import kotlinx.coroutines.flow.Flow


@Dao
interface ChartWithDataDao {
    @Transaction
    @Query("SELECT * FROM CHART_TABLE")
    fun getChartWithData(): Flow<List<ChartWithData>>
}