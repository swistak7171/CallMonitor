package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerConfigurationDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerConfigurationDataModel
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.network.getAllInetAddresses

// region Constants

private const val PORT: Int = 8080
private const val DEFAULT_HOST: String = "0.0.0.0"

// endregion

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

    private fun getHost(): String {
        return getAllInetAddresses()
            .firstOrNull { !it.isLoopbackAddress && it.isSiteLocalAddress }
            ?.hostAddress
            ?.takeIf { it.isNotBlank() }
            ?: DEFAULT_HOST
    }

    // endregion

}