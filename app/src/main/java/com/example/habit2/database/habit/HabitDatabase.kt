package com.example.habit2.database.habit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.habit2.database.habit.dao.DoneDao
import com.example.habit2.database.habit.dao.HabitDao
import com.example.habit2.database.habit.dao.HabitWithDoneDao
import com.example.habit2.database.habit.models.Done
import com.example.habit2.database.habit.models.Habit

@Database(entities = [Habit::class,Done::class], version = 3, exportSchema = false)
@TypeConverters(HabitConverter::class)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun doneDao(): DoneDao

    abstract fun habitWithDoneDao(): HabitWithDoneDao


    companion object {

        private var INSTANCE: HabitDatabase? = null

        fun getInstance(context: Context): HabitDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HabitDatabase::class.java,
                        "habit_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}