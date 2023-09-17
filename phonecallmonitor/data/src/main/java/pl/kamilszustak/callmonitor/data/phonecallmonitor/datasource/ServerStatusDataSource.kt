package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel

interface ServerStatusDataSource {
    suspend fun setStarted()
    suspend fun setStopped()
    suspend fun get(): ServerStatusDataModel
}