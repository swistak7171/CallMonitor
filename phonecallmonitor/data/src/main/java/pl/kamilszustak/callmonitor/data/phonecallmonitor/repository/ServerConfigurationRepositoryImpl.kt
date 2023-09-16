package pl.kamilszustak.callmonitor.data.phonecallmonitor.repository

import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerConfigurationDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerConfigurationRepository

internal class ServerConfigurationRepositoryImpl(
    private val serverConfigurationDataSource: ServerConfigurationDataSource,
) : ServerConfigurationRepository {

    override fun get(): ServerConfigurationDomainModel {
        return serverConfigurationDataSource.get()
            .toDomainModel()
    }

}