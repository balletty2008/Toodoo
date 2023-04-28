package com.example.habit2.database.tracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habit2.database.habit.DoneRepository
import com.example.habit2.database.habit.models.Done
import com.example.habit2.database.tracker.dao.DataDao
import com.example.habit2.database.tracker.models.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {

    private val _dataList = MutableStateFlow<List<Data>>(emptyList())
    val dataList = _dataList.asStateFlow()



    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllData().distinctUntilChanged()
                .collect { listOfData ->
                    if (listOfData.isNullOrEmpty()) {
                        Log.d("Empty", ": Empty list")
                    }else {
                        _dataList.value = listOfData
                    }

                }

        }

    }

    fun addData(data: Data) = viewModelScope.launch { repository.addDone(data) }
    suspend fun getDataForChart(chartListId: Int) = repository.getDataForChart(chartListId)
}