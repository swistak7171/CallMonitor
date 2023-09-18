package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerConfigurationDataModel

/**
 * A data source for server configuration, including host and port information.
 */
interface ServerConfigurationDataSource {

    /**
     * Retrieves the server configuration represented by [ServerConfigurationDataModel], containing
     * such information as host and port.
     */
    fun get(): ServerConfigurationDataModel
}