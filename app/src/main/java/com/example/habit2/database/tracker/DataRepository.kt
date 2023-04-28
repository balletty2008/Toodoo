package com.example.habit2.database.tracker

import com.example.habit2.database.habit.dao.DoneDao
import com.example.habit2.database.habit.models.Done
import com.example.habit2.database.habit.models.Habit
import com.example.habit2.database.tracker.dao.DataDao
import com.example.habit2.database.tracker.models.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataRepository @Inject constructor(private val dataDao: DataDao) {

    fun getAllData(): Flow<List<Data>> = dataDao.getAllData().flowOn(Dispatchers.IO).conflate()

    suspend fun addDone(data: Data) {
        dataDao.insert(data)
    }

  fun getDataForChart(chartListId:Int): List<Data> {
        return dataDao.getDataForChart(chartListId)
    }



}