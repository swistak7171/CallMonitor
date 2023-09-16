package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel

interface ServerStatusDataSource {
    fun setStarted()
    fun setStopped()
    fun get(): ServerStatusDataModel
}