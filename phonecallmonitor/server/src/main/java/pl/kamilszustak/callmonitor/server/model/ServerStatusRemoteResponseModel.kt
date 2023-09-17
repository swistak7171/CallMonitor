package pl.kamilszustak.callmonitor.server.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ServerStatusRemoteResponseModel(
    @SerialName("start")
    val startTimestamp: Instant,
    @SerialName("services")
    val services: List<ServiceRemoteResponseModel>,
)

@Serializable
internal data class ServiceRemoteResponseModel(
    @SerialName("name")
    val name: String,
    @SerialName("uri")
    val uri: String,
)