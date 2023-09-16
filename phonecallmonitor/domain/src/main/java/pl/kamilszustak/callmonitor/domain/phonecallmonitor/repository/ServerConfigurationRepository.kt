package pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel

interface ServerConfigurationRepository {
    fun get(): ServerConfigurationDomainModel
}