package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

import kotlinx.datetime.Instant

data class OngoingPhoneCallDataModel(
    val id: String,
    val startTimestamp: Instant,
    val phoneNumber: String,
)
