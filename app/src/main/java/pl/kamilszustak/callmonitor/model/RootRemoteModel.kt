package pl.kamilszustak.callmonitor.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RootRemoteModel(
    @SerialName("start")
    val startTimestamp: Instant,
    @SerialName("services")
    val services: List<ServiceRemoteModel>,
)

@Serializable
data class ServiceRemoteModel(
    @SerialName("name")
    val name: String,
    @SerialName("uri")
    val uri: String,
)