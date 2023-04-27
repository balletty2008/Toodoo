package com.example.habit2.database.habit

import androidx.lifecycle.LiveData
import com.example.habit2.database.habit.dao.DoneDao
import com.example.habit2.database.habit.dao.HabitWithDoneDao
import com.example.habit2.database.habit.models.Done
import com.example.habit2.database.habit.models.HabitWithDone
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HabitWithDoneRepository@Inject constructor(private val habitWithDoneDao: HabitWithDoneDao) {

    fun getHabitWithDone(): Flow<List<HabitWithDone>> = habitWithDoneDao.getHabitsWithDone().flowOn(Dispatchers.IO).conflate()


}