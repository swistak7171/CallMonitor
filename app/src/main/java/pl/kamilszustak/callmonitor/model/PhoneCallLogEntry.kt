package pl.kamilszustak.callmonitor.model

import kotlin.time.Duration

data class PhoneCallLogEntry(
    val beginningTimestamp: Long,
    val duration: Duration,
    val phoneNumber: String,
    val contactName: String?,
)