package com.example.habit2.database.habit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habit2.database.habit.models.Habit
import com.example.habit2.database.habit.models.HabitWithDone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitWithDoneViewModel @Inject constructor(private val repository: HabitWithDoneRepository) : ViewModel() {

    private val _habitWithDoneList = MutableStateFlow<List<HabitWithDone>>(emptyList())
    val habitWithDoneList = _habitWithDoneList.asStateFlow()



    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getHabitWithDone().distinctUntilChanged()
                .collect { listOfHabitDone ->
                    if (listOfHabitDone.isNullOrEmpty()) {
                        Log.d("Empty", ": Empty list")
                    }else {
                        _habitWithDoneList.value = listOfHabitDone
                    }

                }

        }

    }

    fun getAll(habit: Habit) = viewModelScope.launch { repository.getHabitWithDone() }


}
