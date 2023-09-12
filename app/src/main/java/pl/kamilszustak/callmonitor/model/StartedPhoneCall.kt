package pl.kamilszustak.callmonitor.model

import kotlinx.datetime.Instant
import java.util.UUID

sealed interface PhoneCallStateDomainModel {

    data class StartedPhoneCall(
        val id: UUID,
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallStateDomainModel

    data class EndedPhoneCall(
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallStateDomainModel

}