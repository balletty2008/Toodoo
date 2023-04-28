package com.example.habit2.database.tracker

import com.example.habit2.database.habit.dao.HabitWithDoneDao
import com.example.habit2.database.habit.models.HabitWithDone
import com.example.habit2.database.tracker.dao.ChartWithDataDao
import com.example.habit2.database.tracker.models.ChartWithData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ChartWithDataRepository @Inject constructor(private val chartWithDataDao: ChartWithDataDao) {

    fun getChartWithData(): Flow<List<ChartWithData>> = chartWithDataDao.getChartWithData().flowOn(
        Dispatchers.IO).conflate()


}