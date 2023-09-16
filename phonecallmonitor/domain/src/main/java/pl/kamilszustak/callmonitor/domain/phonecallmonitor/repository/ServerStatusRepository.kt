package pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel

interface ServerStatusRepository {
    fun setStarted()
    fun setStopped()
    fun get(): ServerStatusDomainModel
}