package pl.kamilszustak.callmonitor.server.model

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A model representing a response from the REST API `/` endpoint containing information about
 * the server status and its services.
 */
@Serializable
internal data class ServerStatusRemoteResponseModel(
    @SerialName("start")
    val startTimestamp: Instant,
    @SerialName("services")
    val services: List<ServiceRemoteResponseModel>,
)

/**
 * A model representing a response from the REST API `/` endpoint containing information about
 * a server service.
 */
@Serializable
internal data class ServiceRemoteResponseModel(
    @SerialName("name")
    val name: String,
    @SerialName("uri")
    val uri: String,
)