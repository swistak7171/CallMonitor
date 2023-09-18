package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

import kotlinx.datetime.Instant

/**
 * A model representing a phone call log entry, i.e. a single phone call made or received by the
 * user.
 */
data class PhoneCallLogEntryDataModel(
    val id: String,
    val startTimestamp: Instant,
    val endTimestamp: Instant,
    val phoneNumber: String,
)