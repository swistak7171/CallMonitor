package pl.kamilszustak.callmonitor.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneCallLogEntryRemoteModel(
    @SerialName("beginning")
    val beginningTimestamp: Instant,
    @SerialName("duration")
    val duration: Int,
    @SerialName("number")
    val phoneNumber: String,
    @SerialName("name")
    val contactName: String?,
)
