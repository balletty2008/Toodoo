package com.example.habit2.database.habit


import com.example.habit2.database.habit.dao.HabitDao
import com.example.habit2.database.habit.models.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HabitRepository @Inject constructor(private val habitDao: HabitDao) {

    fun getAllHabit(): Flow<List<Habit>> = habitDao.getAll().flowOn(Dispatchers.IO).conflate()

    suspend fun addHabit(habit: Habit) {
        habitDao.insert(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habitDao.update(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        habitDao.delete(habit)
    }

}
