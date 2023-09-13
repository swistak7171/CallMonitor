package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.ServerConfigurationDomainModel

interface GetServerConfigurationUseCase {
    fun execute(): ServerConfigurationDomainModel
}