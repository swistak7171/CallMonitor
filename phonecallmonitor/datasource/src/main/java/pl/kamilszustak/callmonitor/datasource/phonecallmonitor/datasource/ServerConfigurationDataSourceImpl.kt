package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerConfigurationDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerConfigurationDataModel
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.network.getAllInetAddresses

// region Constants

/**
 * The default port number for the server, aligned with Ktor Server implementation.
 */
private const val PORT: Int = 8080

/**
 * The default host address for the server, aligned with Ktor Server implementation.
 */
private const val DEFAULT_HOST: String = "0.0.0.0"

// endregion

/**
 * A data source for server configuration, including host and port information.
 */
internal class ServerConfigurationDataSourceImpl : ServerConfigurationDataSource {

    // region ServerConfigurationDataSource Implementation

    override fun get(): ServerConfigurationDataModel {
        return ServerConfigurationDataModel(
            host = getHost(),
            port = PORT
        )
    }

    // endregion

    // region Private Methods

    /**
     * Returns the host address of the device as a [String]. If the device has multiple network
     * interfaces, the first one that is not a loopback address and is a site local address
     * is returned. If there is no such address, the [DEFAULT_HOST] address is returned.
     */
    private fun getHost(): String {
        return getAllInetAddresses()
            .firstOrNull { !it.isLoopbackAddress && it.isSiteLocalAddress }
            ?.hostAddress
            ?.takeIf { it.isNotBlank() }
            ?: DEFAULT_HOST
    }

    // endregion

}