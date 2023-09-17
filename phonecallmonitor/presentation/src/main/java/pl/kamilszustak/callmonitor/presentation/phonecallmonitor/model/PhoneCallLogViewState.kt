package pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model

import kotlin.time.Duration

sealed interface PhoneCallLogViewState {
    data object Loading : PhoneCallLogViewState

    data class Success(
        val serverHost: String?,
        val serverPort: Int?,
        val entries: List<PhoneCallLogEntryViewState>,
    ) : PhoneCallLogViewState {

        data class PhoneCallLogEntryViewState(
            val text: String,
            val duration: Duration,
        )
    }
}

