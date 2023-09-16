package pl.kamilszustak.callmonitor.repository

import pl.kamilszustak.callmonitor.datasource.ServerConfigurationDataSource
import pl.kamilszustak.callmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerConfigurationRepository

class ServerConfigurationRepositoryImpl(
    private val serverConfigurationDataSource: ServerConfigurationDataSource,
) : ServerConfigurationRepository {

    override fun get(): ServerConfigurationDomainModel {
        return serverConfigurationDataSource.get()
            .toDomainModel()
    }

}