package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

import kotlinx.datetime.Instant

/**
 * A model representing an ongoing phone call.
 */
data class OngoingPhoneCallDataModel(
    val id: String,
    val startTimestamp: Instant,
    val phoneNumber: String,
)
