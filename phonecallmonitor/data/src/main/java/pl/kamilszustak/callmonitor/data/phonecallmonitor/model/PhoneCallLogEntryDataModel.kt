package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

import kotlinx.datetime.Instant

data class PhoneCallLogEntryDataModel(
    val id: String,
    val startTimestamp: Instant,
    val endTimestamp: Instant,
    val phoneNumber: String,
)