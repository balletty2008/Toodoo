package com.example.habit2.database.tracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habit2.database.habit.HabitWithDoneRepository
import com.example.habit2.database.habit.models.Habit
import com.example.habit2.database.habit.models.HabitWithDone
import com.example.habit2.database.tracker.models.Chart
import com.example.habit2.database.tracker.models.ChartWithData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChartWithDataViewModel @Inject constructor(private val repository: ChartWithDataRepository) : ViewModel() {

    private val _chartWithDataList = MutableStateFlow<List<ChartWithData>>(emptyList())
    val chartWithDataList = _chartWithDataList.asStateFlow()



    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getChartWithData().distinctUntilChanged()
                .collect { listOfChartWithData ->
                    if (listOfChartWithData.isNullOrEmpty()) {
                        Log.d("Empty", ": Empty list")
                    }else {
                        _chartWithDataList.value = listOfChartWithData
                    }

                }

        }

    }

    fun getAll(chart:Chart) = viewModelScope.launch { repository.getChartWithData() }


}
