package pl.kamilszustak.callmonitor.server.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OngoingPhoneCallRemoteResponseModel(
    @SerialName("number")
    val phoneNumber: String,
    @SerialName("name")
    val contactName: String?
) {
    @SerialName("ongoing")
    val isOngoing: Boolean = true
}