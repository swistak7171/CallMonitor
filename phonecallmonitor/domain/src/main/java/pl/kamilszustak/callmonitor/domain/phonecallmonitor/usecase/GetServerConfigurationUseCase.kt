package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerConfigurationRepository

interface GetServerConfigurationUseCase {
    fun execute(): ServerConfigurationDomainModel
}

internal class GetServerConfigurationUseCaseImpl(
    private val serverConfigurationRepository: ServerConfigurationRepository,
) : GetServerConfigurationUseCase {

    override fun execute(): ServerConfigurationDomainModel {
        return serverConfigurationRepository.get()
    }

}