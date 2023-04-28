package com.example.habit2.database.habit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habit2.database.habit.models.Done
import com.example.habit2.database.habit.models.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoneViewModel @Inject constructor(private val repository: DoneRepository) : ViewModel() {

    private val _doneList = MutableStateFlow<List<Done>>(emptyList())
    val doneList = _doneList.asStateFlow()




    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllDone().distinctUntilChanged()
                .collect { listOfDone ->
                    if (listOfDone.isNullOrEmpty()) {
                        Log.d("Empty", ": Empty list")
                    }else {
                        _doneList.value = listOfDone
                    }

                }

        }

    }


    fun addDone(done: Done) = viewModelScope.launch { repository.addDone(done) }


}