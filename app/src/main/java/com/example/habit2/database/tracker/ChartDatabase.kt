package com.example.habit2.database.tracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.habit2.database.habit.dao.DoneDao
import com.example.habit2.database.habit.dao.HabitDao
import com.example.habit2.database.habit.dao.HabitWithDoneDao
import com.example.habit2.database.tracker.dao.ChartDao
import com.example.habit2.database.tracker.dao.ChartWithDataDao
import com.example.habit2.database.tracker.dao.DataDao
import com.example.habit2.database.tracker.models.Chart
import com.example.habit2.database.tracker.models.Data

@Database(entities = [Chart::class, Data::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ChartDatabase : RoomDatabase() {

    abstract fun chartDao(): ChartDao
    abstract fun dataDao(): DataDao

    abstract fun chartWithDataDao(): ChartWithDataDao



    companion object {

        private var INSTANCE: ChartDatabase? = null

        fun getInstance(context: Context): ChartDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ChartDatabase::class.java,
                        "chart_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}