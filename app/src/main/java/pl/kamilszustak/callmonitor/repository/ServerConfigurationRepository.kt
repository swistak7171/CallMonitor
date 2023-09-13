package pl.kamilszustak.callmonitor.repository

import pl.kamilszustak.callmonitor.model.ServerConfigurationDomainModel

interface ServerConfigurationRepository {
    fun get(): ServerConfigurationDomainModel
}