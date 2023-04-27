package com.example.habit2.database.habit.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.habit2.database.habit.models.Habit
import java.time.LocalDate


@Entity(
    tableName = "habitData", foreignKeys = [ForeignKey(
        entity = Habit::class,
        parentColumns = arrayOf("habitId"),
        childColumns = arrayOf("habit_data_id"),
        onDelete = ForeignKey.CASCADE,
    )]
)
data class HabitWeekData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "habit_data_id", index = true) val habitDataId: Int,
    @ColumnInfo(name = "date", index = true)
    val date: LocalDate,
    @ColumnInfo(name = "status", index = true)
    val isDone: Boolean,
)