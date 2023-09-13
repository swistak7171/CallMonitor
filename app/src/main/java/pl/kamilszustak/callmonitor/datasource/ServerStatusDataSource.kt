package pl.kamilszustak.callmonitor.datasource

import pl.kamilszustak.callmonitor.model.ServerStatusDataModel

interface ServerStatusDataSource {
    fun setStarted()
    fun setStopped()
    fun get(): ServerStatusDataModel
}