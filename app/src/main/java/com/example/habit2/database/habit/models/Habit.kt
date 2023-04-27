package com.example.habit2.database.habit.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "habit_table")
data class Habit @RequiresApi(Build.VERSION_CODES.O) constructor(

    @PrimaryKey(autoGenerate = true)
    val habitId: Int = 0,

    @ColumnInfo(name = "habit_title")
    val habitTitle: String,




)