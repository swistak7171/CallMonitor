package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

import kotlinx.datetime.Instant

sealed interface PhoneCallEventDomainModel {

    data class PhoneCallStart(
        val id: String,
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDomainModel

    data class PhoneCallEnd(
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDomainModel

}