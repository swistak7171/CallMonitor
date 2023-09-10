package pl.kamilszustak.callmonitor.model

sealed interface PhoneCallState {

    data class Started(
        val phoneNumber: String,
        val timestamp: Long,
    ) : PhoneCallState

    data class Ended(
        val phoneNumber: String,
        val timestamp: Long,
    ) : PhoneCallState


}