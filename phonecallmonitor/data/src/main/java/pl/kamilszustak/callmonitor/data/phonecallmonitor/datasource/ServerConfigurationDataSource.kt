package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerConfigurationDataModel

interface ServerConfigurationDataSource {
    fun get(): ServerConfigurationDataModel
}