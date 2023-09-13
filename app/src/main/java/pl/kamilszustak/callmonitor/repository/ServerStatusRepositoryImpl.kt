package pl.kamilszustak.callmonitor.repository

import pl.kamilszustak.callmonitor.datasource.ServerStatusDataSource
import pl.kamilszustak.callmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.model.ServerStatusDomainModel

class ServerStatusRepositoryImpl(
    private val serverStatusDataSource: ServerStatusDataSource,
) : ServerStatusRepository {

    override fun setStarted() {
        serverStatusDataSource.setStarted()
    }

    override fun setStopped() {
        serverStatusDataSource.setStopped()
    }

    override fun get(): ServerStatusDomainModel {
        return serverStatusDataSource.get()
            .toDomainModel()
    }
}