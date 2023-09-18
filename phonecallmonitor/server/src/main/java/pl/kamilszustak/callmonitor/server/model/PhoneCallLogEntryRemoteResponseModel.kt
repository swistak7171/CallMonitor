package pl.kamilszustak.callmonitor.server.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A model representing a response from the REST API `/log` endpoint containing information about
 * a phone call log entry.
 */
@Serializable
internal data class PhoneCallLogEntryRemoteResponseModel(
    @SerialName("beginning")
    val beginningTimestamp: Instant,
    @SerialName("duration")
    val duration: Int,
    @SerialName("number")
    val phoneNumber: String,
    @SerialName("name")
    val contactName: String?,
    @SerialName("timesQueried")
    val timesQueried: Int,
)
