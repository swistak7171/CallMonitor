package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

import kotlinx.datetime.Instant

sealed interface PhoneCallEventDataModel {

    data class PhoneCallStart(
        val id: String,
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDataModel

    data class PhoneCallEnd(
        val timestamp: Instant,
        val phoneNumber: String,
    ) : PhoneCallEventDataModel

}