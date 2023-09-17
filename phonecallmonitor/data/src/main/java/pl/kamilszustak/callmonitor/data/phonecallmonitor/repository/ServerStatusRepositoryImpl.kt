package pl.kamilszustak.callmonitor.data.phonecallmonitor.repository

import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerStatusDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerStatusRepository

internal class ServerStatusRepositoryImpl(
    private val serverStatusDataSource: ServerStatusDataSource,
) : ServerStatusRepository {

    // region ServerStatusRepository Implementation

    override suspend fun setStarted() {
        serverStatusDataSource.setStarted()
    }

    override suspend fun setStopped() {
        serverStatusDataSource.setStopped()
    }

    override suspend fun get(): ServerStatusDomainModel {
        return serverStatusDataSource.get()
            .toDomainModel()
    }

    // endregion

}