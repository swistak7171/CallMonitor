package pl.kamilszustak.presentation.model

import kotlin.time.Duration

data class MainViewState(
    val serverHost: String? = null,
    val serverPort: Int? = null,
    val logEntries: List<LogEntryViewState> = emptyList(),
)

data class LogEntryViewState(
    val text: String,
    val duration: Duration,
)
