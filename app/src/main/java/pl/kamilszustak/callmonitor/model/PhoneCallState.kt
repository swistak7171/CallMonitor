package pl.kamilszustak.callmonitor.model

sealed interface PhoneCallState {

    @JvmInline
    value class Started(
        val phoneNumber: String,
    ) : PhoneCallState

    @JvmInline
    value class Ended(
        val phoneNumber: String,
    ) : PhoneCallState

}