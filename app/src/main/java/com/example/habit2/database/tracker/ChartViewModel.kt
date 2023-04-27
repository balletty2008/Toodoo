package com.example.habit2.database.tracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habit2.database.habit.HabitRepository
import com.example.habit2.database.habit.models.Habit
import com.example.habit2.database.tracker.models.Chart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(private val repository: ChartRepository) : ViewModel() {

    private val _chartList = MutableStateFlow<List<Chart>>(emptyList())
    val chartList = _chartList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().distinctUntilChanged()
                .collect { listOfCharts ->
                    if (listOfCharts.isNullOrEmpty()) {
                        Log.d("Empty", ": Empty list")
                    }else {
                        _chartList.value = listOfCharts
                    }

                }

        }

    }

    fun getAll(chart: Chart) = viewModelScope.launch { repository.getAll() }
    fun addChart(chart: Chart) = viewModelScope.launch { repository.addChart(chart) }
    fun deleteChart(chart: Chart) = viewModelScope.launch { repository.deleteChart(chart) }

}
