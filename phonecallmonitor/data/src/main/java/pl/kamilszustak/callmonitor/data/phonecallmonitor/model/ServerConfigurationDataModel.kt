package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

/**
 * A model representing a server configuration. It contains information about the [host] and [port].
 */
data class ServerConfigurationDataModel(
    val host: String,
    val port: Int,
)
