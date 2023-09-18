package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

import kotlinx.datetime.Instant

/**
 * A model representing a phone call event.
 */
sealed interface PhoneCallEventDataModel {

    /**
     * A model representing a phone call start.
     */
    data class PhoneCallStart(
        val id: String,
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDataModel

    /**
     * A model representing a phone call end.
     */
    data class PhoneCallEnd(
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDataModel

}