package com.example.habit2.database.habit

import androidx.room.TypeConverter
import java.time.LocalDate


class HabitConverter {
    @TypeConverter
    fun fromDateStamp(date: String): LocalDate {
        return LocalDate.parse(date)
    }

    @TypeConverter
    fun toDateStamp(date: LocalDate): String {
        return date.toString()
    }
}