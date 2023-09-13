package pl.kamilszustak.callmonitor.model

import kotlinx.datetime.Instant
import java.util.UUID

sealed interface PhoneCallEventDataModel {

    data class PhoneCallStart(
        val id: UUID,
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDataModel

    data class PhoneCallEnd(
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDataModel

}