package pl.kamilszustak.callmonitor.data.phonecallmonitor.repository

import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerConfigurationDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerConfigurationRepository

/**
 * A repository for retrieving server configuration, including host and port information.
 */
internal class ServerConfigurationRepositoryImpl(
    private val serverConfigurationDataSource: ServerConfigurationDataSource,
) : ServerConfigurationRepository {

    // region ServerConfigurationRepository Implementation

    override fun get(): ServerConfigurationDomainModel {
        return serverConfigurationDataSource.get()
            .toDomainModel()
    }

    // endregion

}