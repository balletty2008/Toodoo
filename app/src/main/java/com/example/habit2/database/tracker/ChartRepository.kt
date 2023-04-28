package com.example.habit2.database.tracker

import com.example.habit2.database.habit.dao.HabitDao
import com.example.habit2.database.habit.models.Habit
import com.example.habit2.database.tracker.dao.ChartDao
import com.example.habit2.database.tracker.models.Chart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChartRepository @Inject constructor(private val chartDao: ChartDao) {

    fun getAll(): Flow<List<Chart>> = chartDao.getAll().flowOn(Dispatchers.IO).conflate()

    suspend fun addChart(chart: Chart) {
        chartDao.insert(chart)
    }

    suspend fun updateChart(chart: Chart) {
        chartDao.update(chart)
    }

    suspend fun deleteChart(chart: Chart) {
        chartDao.delete(chart)
    }



}