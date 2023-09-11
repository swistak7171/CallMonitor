package pl.kamilszustak.callmonitor.model

import kotlinx.datetime.Instant
import java.util.UUID
import kotlin.time.Duration

data class PhoneCallLogEntryDomainModel(
    val id: UUID,
    val startTimestamp: Instant,
    val endTimestamp: Instant,
    val phoneNumber: String,
    val contactName: String?
) {
    val duration: Duration
        get() = endTimestamp - startTimestamp
}