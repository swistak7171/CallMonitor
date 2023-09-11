package pl.kamilszustak.callmonitor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
data class PhoneCallLogEntryRemoteModel(
    @SerialName("beginning")
    val beginningTimestamp: Long,
    @SerialName("duration")
    val duration: Duration,
    @SerialName("number")
    val phoneNumber: String,
    @SerialName("name")
    val contactName: String?,
)
