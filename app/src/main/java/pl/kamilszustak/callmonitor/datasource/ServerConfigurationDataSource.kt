package pl.kamilszustak.callmonitor.datasource

import pl.kamilszustak.callmonitor.model.ServerConfigurationDataModel

interface ServerConfigurationDataSource {
    fun get(): ServerConfigurationDataModel
}