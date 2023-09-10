package pl.kamilszustak.callmonitor.model

import kotlin.time.Duration

data class MainViewState(
    val logEntries: List<LogEntryViewState> = emptyList(),
)

data class LogEntryViewState(
    val text: String,
    val duration: Duration,
)
