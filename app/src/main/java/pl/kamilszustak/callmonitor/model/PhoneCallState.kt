package pl.kamilszustak.callmonitor.model

import kotlinx.datetime.Instant

sealed interface PhoneCallState {

    data class Started(
        val phoneNumber: String,
        val timestamp: Instant,
    ) : PhoneCallState

    data class Ended(
        val phoneNumber: String,
        val timestamp: Instant,
    ) : PhoneCallState


}