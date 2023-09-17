package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class PhoneCallLogEntryDomainModel(
    val id: String,
    val metadata: PhoneCallMetadataDomainModel,
    val startTimestamp: Instant,
    val endTimestamp: Instant,
    val phoneNumber: String,
    val contactName: String?
) {
    val duration: Duration
        get() = (endTimestamp - startTimestamp)
            .inWholeSeconds
            .seconds
}
