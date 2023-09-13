package pl.kamilszustak.callmonitor.repository

import pl.kamilszustak.callmonitor.model.ServerStatusDomainModel

interface ServerStatusRepository {
    fun setStarted()
    fun setStopped()
    fun get(): ServerStatusDomainModel
}