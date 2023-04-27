package com.example.habit2.database.tracker.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "chart_table")
data class Chart(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "chart_id")
    val chartId: Long = 0,

    @ColumnInfo(name = "chart_title")
    val chartTitle: String,

)