package pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model

import kotlin.time.Duration

/**
 * A model representing the view state of the phone call log.
 */
sealed interface PhoneCallLogViewState {

    /**
     * A model representing the view state of the phone call log when data is being loaded.
     */
    data object Loading : PhoneCallLogViewState

    /**
     * A model representing the view state of the phone call log when data has been loaded
     * successfully and is ready to be displayed.
     */
    data class Success(
        val serverHost: String,
        val serverPort: Int,
        val entries: List<PhoneCallLogEntryViewState>,
    ) : PhoneCallLogViewState
}

/**
 * A model representing the view state of the phone call log entry.
 */
data class PhoneCallLogEntryViewState(
    val id: String,
    val phoneNumber: String,
    val contactName: String?,
    val duration: Duration,
)
