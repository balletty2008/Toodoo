package com.example.habit2.database.habit

import android.util.Log
import androidx.lifecycle.*
import com.example.habit2.database.habit.models.Habit
import com.example.habit2.database.habit.models.HabitWeekData
import com.example.habit2.database.habit.repository.HabitRepository
import com.example.habit2.database.habit.repository.HabitWeekDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val repository: HabitRepository,
    private val dataRepository: HabitWeekDataRepository,
) : ViewModel() {

    private val _habitList = MutableStateFlow<List<Habit>>(emptyList())
    val habitList = _habitList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllHabit().distinctUntilChanged()
                .collect { listOfHabits ->
                    if (listOfHabits.isNullOrEmpty()) {
                        Log.d("Empty", ": Empty list")
                    }else {
                        _habitList.value = listOfHabits
                    }

                }

        }

    }

    fun addHabitData(habitData: HabitWeekData) = viewModelScope.launch { dataRepository.addWeekData(habitData) }
    fun getAll(habit: Habit) = viewModelScope.launch { repository.getAllHabit() }
    fun addHabit(habit: Habit) = viewModelScope.launch { repository.addHabit(habit) }
    fun updateHabit(habit: Habit) = viewModelScope.launch { repository.updateHabit(habit) }
    fun deleteHabit(habit: Habit) = viewModelScope.launch { repository.deleteHabit(habit) }

}
