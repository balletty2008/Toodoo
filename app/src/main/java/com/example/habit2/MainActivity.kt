package com.example.habit2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habit2.database.habit.DoneViewModel
import com.example.habit2.database.habit.HabitViewModel
import com.example.habit2.database.habit.HabitWithDoneViewModel
import com.example.habit2.database.tracker.ChartDatabase
import com.example.habit2.database.tracker.ChartViewModel
import com.example.habit2.database.tracker.ChartWithDataViewModel
import com.example.habit2.database.tracker.DataViewModel
import com.example.habit2.ui.theme.Habit2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Habit2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val habitViewModel = viewModel<HabitViewModel>()
                    val doneViewModel = viewModel<DoneViewModel>()
                    val habitWithDoneViewModel = viewModel<HabitWithDoneViewModel>()
                    val chartViewModel = viewModel<ChartViewModel>()
                    val dataViewModel = viewModel<DataViewModel>()
                    val chartWithDataViewModel = viewModel<ChartWithDataViewModel>()
                    val chartDb = ChartDatabase
                    HabitTracker(habitViewModel,
                        doneViewModel,
                        habitWithDoneViewModel,
                        chartViewModel,
                        dataViewModel,
                        chartWithDataViewModel,

                    )
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Composable
fun HabitTracker(
    habitViewModel: HabitViewModel,
    doneViewModel: DoneViewModel,
    habitWithDoneViewModel: HabitWithDoneViewModel,
    chartViewModel: ChartViewModel,
    dataViewModel: DataViewModel,
    chartWithDataViewModel: ChartWithDataViewModel,

) {
    val habitList = habitViewModel.habitList.collectAsState().value
    val doneList = doneViewModel.doneList.collectAsState().value
    val chartList = chartViewModel.chartList.collectAsState().value
    val habitWithDoneList = habitWithDoneViewModel.habitWithDoneList.collectAsState().value
    val dataList = dataViewModel.dataList.collectAsState().value
    val chartWithDataList = chartWithDataViewModel.chartWithDataList.collectAsState().value


    HabitScreen(habits = habitList,
        onAddHabit = { habitViewModel.addHabit(it) },
        habitViewModel = habitViewModel,
        doneViewModel = doneViewModel,
        habitWithDoneViewModel = habitWithDoneViewModel,
        chartViewModel = chartViewModel,
        charts = chartList,
        habitWithDone = habitWithDoneList,
        doneData = doneList,
        data = dataList,
        dataViewModel = dataViewModel,
        chartWithDataList = chartWithDataList)

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Habit2Theme {

    }
}