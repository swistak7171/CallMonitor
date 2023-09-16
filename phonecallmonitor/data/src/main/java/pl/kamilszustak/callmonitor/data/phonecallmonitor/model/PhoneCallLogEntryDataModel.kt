package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

import kotlinx.datetime.Instant
import java.util.UUID

data class PhoneCallLogEntryDataModel(
    val id: UUID,
    val startTimestamp: Instant,
    val endTimestamp: Instant,
    val phoneNumber: String,
)