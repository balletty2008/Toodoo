package com.example.habit2

import java.util.*
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habit2.database.habit.models.Done
import com.example.habit2.enums.ColorItem
import com.example.habit2.enums.WeekDay
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import java.time.LocalDate
import com.example.habit2.database.habit.models.Habit as Habit1
import com.example.habit2.database.habit.*
import com.example.habit2.database.habit.models.HabitWithDone
import com.example.habit2.database.tracker.ChartViewModel
import com.example.habit2.database.tracker.models.Chart


@Composable
fun WeekDayIcon(
    day: WeekDay,
    habits: com.example.habit2.database.habit.models.Habit,
    id: Int, doneViewModel: DoneViewModel,
    habitWithDoneViewModel: HabitWithDoneViewModel,
    data: HabitWithDone,
    doneData: List<Done>
) {
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    var clicked by remember { mutableStateOf(false) }
    var date = LocalDate.now()
    val done = Done(0,id,date,true,day.num)
    val dayOfWeek = day.num

   val status = data.done.find { it.btn == dayOfWeek}
   /* if (doneData.any { it.btn == dayOfWeek }) {
       clicked = true
    }*/


    Text(text = day.title,
        fontSize = 12.sp, color = Color.Black,
        modifier = Modifier
            .size(16.dp)
            .clickable {
                doneViewModel.addDone(done)
            }
            .drawBehind {
                drawCircle(
                    color = if (status?.done == true) Color.Cyan else Color.LightGray,
                    radius = 40.dp.value
                )
            },
        textAlign = TextAlign.Center
    )



}



@Composable
fun HabitRow(
    habit: HabitWithDone,
    onDeleteHabit: (com.example.habit2.database.habit.models.Habit) -> Unit,
    onUpdateHabit: (com.example.habit2.database.habit.models.Habit) -> Unit,
    doneViewModel: DoneViewModel,
    habitWithDoneViewModel: HabitWithDoneViewModel,
    doneData: List<Done>,
) {



    Column(modifier = Modifier
        .fillMaxWidth()
        .height(72.dp)
        .padding(bottom = 5.dp)
        .background(Color(244, 244, 244))
    ) {

        Row(
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Text(text = "${habit.habit.habitTitle}",
                modifier = Modifier
                    .weight(8f)
                    .padding(start = 3.dp),
                fontSize = 22.sp)

            IconButton(
                onClick = {
                    onDeleteHabit(habit.habit)
                    onUpdateHabit(habit.habit)
                          },
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f)
            ) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    tint = Color.Black)
            }

        }



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0 until 7) {
                WeekDayIcon(WeekDay.getDay(i),habit.habit,habit.habit.habitId, doneViewModel = doneViewModel,
                    habitWithDoneViewModel = habitWithDoneViewModel,data= habit,doneData = doneData
                )
            }

            /*for (i in 1..7) {
                var clicked by remember { mutableStateOf(false) }
                val color = if (clicked) Color.Cyan else  Color.LightGray

               IconButton(
                    onClick = {
                        clicked = !clicked
                    }

                ) {
                    Icon(
                        modifier = Modifier
                            .height(28.dp)
                            .width(28.dp),
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "",

                        tint = color,

                    )
                }
            }*/



        }

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalComposeUiApi
@Composable
fun HabitScreen(
    habits: List<com.example.habit2.database.habit.models.Habit>,
    onAddHabit: (com.example.habit2.database.habit.models.Habit) -> Unit,
    habitViewModel: HabitViewModel,
    doneViewModel: DoneViewModel,
    habitWithDoneViewModel: HabitWithDoneViewModel,
    chartViewModel: ChartViewModel,
    charts: List<Chart>,
    habitWithDone: List<HabitWithDone>,
    doneData: List<Done>

) {


    var habitTitle = remember { mutableStateOf("") }
    val context = LocalContext.current
    var chartTitle = remember { mutableStateOf("") }



    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp)
                .height(36.dp)
        ) {
            val openDialog = remember {
                mutableStateOf(false)
            }

            Text(
                text = "Habit",
                textAlign = TextAlign.Left,
                modifier = Modifier.weight(1f),
                fontSize = 28.sp,
            )
            IconButton(
                onClick = { openDialog.value = true },
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = Color.Black
                )
            }

            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = { openDialog.value = false },
                    title = {
                        Text(
                            text = "Create Habit",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    },
                    text = {
                        Column() {
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                value = habitTitle.value,
                                singleLine = true,
                                label = { Text("habit title", color = Color.Black) },
                                onValueChange = {
                                    habitTitle.value = it
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Black,
                                    unfocusedBorderColor = Color.Black
                                )
                            )

                        }

                    },
                    confirmButton = {
                        IconButton(onClick = {
                            openDialog.value = false
                            if (habitTitle.value.isNotEmpty()) {
                                onAddHabit(
                                    Habit1(0, habitTitle.value)
                                )
                                Toast.makeText(
                                    context, "Habit Added",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = "save")
                        }
                    },
                    dismissButton = {
                        IconButton(onClick = { openDialog.value = false }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "cancel"
                            )
                        }
                    }


                )
            }

        }




        Card(
            modifier = Modifier
                .padding(8.dp) // margin
                .border(2.dp, Color.White) // outer border
                .padding(8.dp) // space between the borders
                .border(2.dp, Color.White) // inner border
                .padding(8.dp)
                .fillMaxWidth()
            ,
            backgroundColor = Color(238, 238, 238)

        ) {
            Column(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 36.dp)
            ) {
                if (habits.isEmpty()){
                    Text(text = "No habit yet:)", color = Color.LightGray,style = typography.h6, modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
                }else{

                    habitWithDone.forEach() { habit ->
                        HabitRow(
                            habit = habit,
                            onDeleteHabit = { habitViewModel.deleteHabit(it) },
                            onUpdateHabit = {habitViewModel.updateHabit(it)},
                            doneViewModel =doneViewModel,
                            habitWithDoneViewModel = habitWithDoneViewModel,
                            doneData = doneData
                        )
                    }
                }


                }

            }

        //Tracker part


        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp)
                    .height(36.dp)
            ) {
                val openDialog = remember {
                    mutableStateOf(false)
                }
                Text(
                    text = "Tracker",
                    textAlign = TextAlign.Left,
                    modifier = Modifier.weight(1f),
                    fontSize = 28.sp,
                )
                IconButton(
                    onClick = {
                        openDialog.value = true
                    },
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        modifier = Modifier.size(36.dp),
                        tint = Color.Black)
                }

                if (openDialog.value) {

                    AlertDialog(
                        onDismissRequest = { openDialog.value = false },
                        title = {
                            Text(
                                text = "Create Tracker",
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        },
                        text = {
                            Column() {
                                OutlinedTextField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp),
                                    value = chartTitle.value,
                                    singleLine = true,
                                    label = { Text("Tracker title", color = Color.Black) },
                                    onValueChange = {
                                        chartTitle.value = it
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedBorderColor = Color.Black,
                                        unfocusedBorderColor = Color.Black
                                    )
                                )

                            }

                        },
                        confirmButton = {
                            IconButton(onClick = {
                                openDialog.value = false
                                if (chartTitle.value.isNotEmpty()) {
                                    chartViewModel.addChart(Chart(0,chartTitle.value))
                                    Toast.makeText(
                                        context, "Tracker Added",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }
                            ) {
                                Icon(imageVector = Icons.Default.Check, contentDescription = "save")
                            }
                        },
                        dismissButton = {
                            IconButton(onClick = { openDialog.value = false }) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "cancel"
                                )
                            }
                        }


                    )
                }

            }




            //Tracker Row

            Column() {
               charts.forEach { charts ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp) // margin
                            .border(2.dp, Color.White) // outer border
                            .padding(8.dp) // space between the borders
                            .border(2.dp, Color.White) // inner border
                            .padding(8.dp) ,
                        backgroundColor = Color(	238,238,238)

                    ) {
                        TrackerRow(charts,chartViewModel)
                    }

                }


            }


        }

    }


    }


@Composable
fun TrackerRow(chars:Chart,chartViewModel: ChartViewModel) {

    val chartEntryModel = entryModelOf(1f,3f,5f,2f,7f,5f,7f)
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color(244, 244, 244))
    ) {

        Row(modifier = Modifier.padding(top = 6.dp, bottom = 8.dp)) {

            val openEditDialog = remember {
                mutableStateOf(false)
            }



            Text(text = "${chars.chartTitle}",
                modifier = Modifier.weight(8f),
                fontSize = 22.sp)

            IconButton(
                onClick = { openEditDialog.value = true },
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f)
            ) {
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    tint = Color.Black)
            }

            IconButton(
                onClick = {
                          chartViewModel.deleteChart(Chart(chars.chartId,chars.chartTitle))
                },
                modifier = Modifier
                    .size(30.dp)
                    .weight(1f)
            ) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    tint = Color.Black)
            }

            if (openEditDialog.value) {

                AlertDialog(
                    onDismissRequest = { openEditDialog.value = false },
                    title = {
                        Text(
                            text = "Edit data",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    },
                    text = {
                        Column() {
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                value = "habitTitle.value",
                                singleLine = true,
                                label = { Text("Today's data", color = Color.Black) },
                                onValueChange = {
                                    //habitTitle.value = it
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Black,
                                    unfocusedBorderColor = Color.Black
                                )
                            )

                        }

                    },
                    confirmButton = {
                        IconButton(onClick = {
                            openEditDialog.value = false
                           /* if (habitTitle.value.isNotEmpty()) {
                                onAddHabit(
                                    Habit1(0, habitTitle.value, false)
                                )
                                Toast.makeText(
                                    context, "Tracker Added",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }*/

                        }
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = "save")
                        }
                    },
                    dismissButton = {
                        IconButton(onClick = { openEditDialog.value = false }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "cancel"
                            )
                        }
                    }


                )
            }
        }


        Box(

            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp, 210.dp)
                .padding(5.dp),

            ) {
            Chart(
                chart = lineChart(),
                model = chartEntryModel,
                startAxis = startAxis(),
                bottomAxis = bottomAxis()
            )
        }

    }
}


@Composable
    fun ColorItems(color: ColorItem) {
        var colors by remember { mutableStateOf(color) }

        Box(modifier = Modifier
            .padding(4.dp)
            .size(42.dp)
            .aspectRatio(1f)
            .background(Color(color.num), shape = CircleShape)
            .clickable {
                colors.check = !colors.check
            }
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(40.dp),
                color = if (colors.check) Color.Black else Color.White
            )

        )


    }




