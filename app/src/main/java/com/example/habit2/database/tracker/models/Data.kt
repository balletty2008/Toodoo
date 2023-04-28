package com.example.habit2.database.tracker.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity( tableName = "data",foreignKeys = [ForeignKey(
    entity = Chart::class,
    parentColumns = arrayOf("chart_id"),
    childColumns = arrayOf("chartListId"),
    onDelete = ForeignKey.CASCADE,
)])
data class Data(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "data_id")
    val dataId: Int ,
    @ColumnInfo(name = "chartListId")
    var chartListId: Int,
    val date:LocalDate,
    val value: Float = 0f
)