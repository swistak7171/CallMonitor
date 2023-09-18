package pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel

/**
 * A repository for retrieving server configuration, including host and port information.
 */
interface ServerConfigurationRepository {

    /**
     * Returns the server configuration represented by [ServerConfigurationDomainModel], containing
     * such information as host and port.
     */
    fun get(): ServerConfigurationDomainModel
}