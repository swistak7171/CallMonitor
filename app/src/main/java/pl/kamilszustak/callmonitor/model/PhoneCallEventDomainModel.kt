package pl.kamilszustak.callmonitor.model

import kotlinx.datetime.Instant
import java.util.UUID

sealed interface PhoneCallEventDomainModel {

    data class PhoneCallStart(
        val id: UUID,
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDomainModel

    data class PhoneCallEnd(
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDomainModel

}