package com.example.habit2.database.habit

import com.example.habit2.database.tracker.models.Data
import com.patrykandpatrick.vico.core.DEF_THREAD_POOL_SIZE
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.ChartModelProducer
import com.patrykandpatrick.vico.core.entry.diff.DefaultDiffProcessor
import com.patrykandpatrick.vico.core.entry.diff.DiffProcessor
import java.util.concurrent.Executor
import java.util.concurrent.Executors

abstract class ChartEntryModelProducer(
    entryCollections: List<List<Data>>,
    backgroundExecutor: Executor = Executors.newFixedThreadPool(DEF_THREAD_POOL_SIZE),
    diffProcessor: DiffProcessor<ChartEntry> = DefaultDiffProcessor()
) : ChartModelProducer<ChartEntryModel>