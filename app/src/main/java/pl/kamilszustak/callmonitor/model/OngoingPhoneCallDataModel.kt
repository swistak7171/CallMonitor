package pl.kamilszustak.callmonitor.model

import kotlinx.datetime.Instant
import java.util.UUID

data class OngoingPhoneCallDataModel(
    val id: UUID,
    val timestamp: Instant,
    val phoneNumber: String,
)
