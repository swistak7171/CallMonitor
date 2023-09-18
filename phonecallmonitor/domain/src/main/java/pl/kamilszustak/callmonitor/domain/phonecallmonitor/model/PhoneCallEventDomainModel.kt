package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

import kotlinx.datetime.Instant

/**
 * A model representing a phone call event.
 */
sealed interface PhoneCallEventDomainModel {

    /**
     * A model representing a phone call start.
     */
    data class PhoneCallStart(
        val id: String,
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDomainModel

    /**
     * A model representing a phone call end.
     */
    data class PhoneCallEnd(
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDomainModel

}