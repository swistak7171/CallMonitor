package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.repository.ServerConfigurationRepository

class GetServerConfigurationUseCaseImpl(
    private val serverConfigurationRepository: ServerConfigurationRepository,
) : GetServerConfigurationUseCase {

    override fun execute(): ServerConfigurationDomainModel {
        return serverConfigurationRepository.get()
    }

}