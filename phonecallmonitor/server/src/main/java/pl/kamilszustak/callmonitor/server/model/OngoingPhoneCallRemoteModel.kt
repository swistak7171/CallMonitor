package pl.kamilszustak.callmonitor.server.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OngoingPhoneCallRemoteModel(
    @SerialName("number")
    val phoneNumber: String,
    @SerialName("name")
    val contactName: String?
) {
    @SerialName("ongoing")
    val isOngoing: Boolean = true
}