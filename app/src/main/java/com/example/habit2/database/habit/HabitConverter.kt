package com.example.habit2.database.habit


class HabitConverter {
<<<<<<< Updated upstream

=======
    @TypeConverter
    fun fromDateStamp(date: String): LocalDate {
        return LocalDate.parse(date)
    }

    @TypeConverter
    fun toDateStamp(date: LocalDate): String {
        return date.toString()
    }
>>>>>>> Stashed changes
}