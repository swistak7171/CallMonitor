package pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel

interface ServerStatusRepository {
    suspend fun setStarted()
    suspend fun setStopped()
    suspend fun get(): ServerStatusDomainModel
}