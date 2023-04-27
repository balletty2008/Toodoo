package com.example.habit2.database.habit.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.habit2.database.habit.models.Habit
import com.example.habit2.database.habit.models.HabitWeekData

data class HabitWithData(
    @Embedded val habit: Habit,
    @Relation(
        parentColumn = "habitId",
        entityColumn = "habit_data_id"
    )
    val data: List<HabitWeekData>
)
