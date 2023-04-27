package com.example.habit2.database.habit.repository


import com.example.habit2.database.habit.HabitViewModel
import com.example.habit2.database.habit.dao.HabitWeekDataDao
import com.example.habit2.database.habit.models.HabitWeekData
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@InstallIn(ViewModelComponent::class)
@Module
class HabitWeekDataRepository @Inject constructor  (private val habitWeekDataDao: HabitWeekDataDao)  {

    suspend fun addWeekData(habit: HabitWeekData) {
        habitWeekDataDao.insert(habit)
    }

}