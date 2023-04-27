package com.example.habit2.database.tracker.models

import androidx.room.Embedded
import androidx.room.Relation

data class ChartWithData(
    @Embedded val chart: Chart,
    @Relation(
        parentColumn = "chart_id",
        entityColumn = "chartListId"
    )
    val data: List<Data>
)
