package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

/**
 * A model representing a server configuration. It contains information about the [host] and [port].
 */
data class ServerConfigurationDomainModel(
    val host: String,
    val port: Int,
)
