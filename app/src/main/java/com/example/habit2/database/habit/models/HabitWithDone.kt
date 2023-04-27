package com.example.habit2.database.habit.models

import androidx.room.Embedded
import androidx.room.Relation

data class HabitWithDone(
    @Embedded val habit: Habit,
    @Relation(
        parentColumn = "habitId",
        entityColumn = "habit_id"
    )
    val done: List<Done>
)
