package com.example.habit2.database.habit

import com.example.habit2.database.habit.dao.DoneDao
import com.example.habit2.database.habit.dao.HabitDao
import com.example.habit2.database.habit.models.Done
import com.example.habit2.database.habit.models.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DoneRepository @Inject constructor(private val doneDao: DoneDao) {

    fun getAllDone(): Flow<List<Done>> = doneDao.getAllDone().flowOn(Dispatchers.IO).conflate()

    suspend fun addDone(done: Done) {
        doneDao.insert(done)
    }

    fun getDoneForHabit(habitId:Int): Flow<List<Done>> {
        return doneDao.getDoneForHabit(habitId)
    }


}