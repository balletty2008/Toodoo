package com.example.habit2.database.habit.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "done", foreignKeys = [ForeignKey(
        entity = Habit::class,
        parentColumns = arrayOf("habitId"),
        childColumns = arrayOf("habit_id"),
        onDelete = ForeignKey.CASCADE,
    )]
)
data class Done(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "habit_id", index = true) val habitDoneId: Int,
    @ColumnInfo(name = "date", index = true)
    val date: LocalDate = LocalDate.now(),
    @ColumnInfo(name = "done", index = true)
    var done: Boolean,
    var btn : Int
)