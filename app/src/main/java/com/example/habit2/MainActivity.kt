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
import com.example.habit2.database.habit.HabitViewModel
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
                    HabitTracker(habitViewModel)
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Composable
fun HabitTracker(habitViewModel: HabitViewModel) {
    val habitList = habitViewModel.habitList.collectAsState().value

    HabitScreen(habits = habitList,
        onAddHabit = { habitViewModel.addHabit(it) },
        onUpdateHabit = {habitViewModel.updateHabit(it)},
        habitViewModel = habitViewModel,
        onAddData = {habitViewModel.addHabitData(it)}
    )

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Habit2Theme {

    }
}