package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerConfigurationRepository

/**
 * A use case for retrieving the server configuration, including host and port information.
 */
interface GetServerConfigurationUseCase {

    /**
     * Returns the server configuration represented by [ServerConfigurationDomainModel], containing
     * such information as host and port.
     */
    fun execute(): ServerConfigurationDomainModel
}

/**
 * A use case for retrieving the server configuration, including host and port information.
 */
internal class GetServerConfigurationUseCaseImpl(
    private val serverConfigurationRepository: ServerConfigurationRepository,
) : GetServerConfigurationUseCase {

    // region GetServerConfigurationUseCase Implementation

    override fun execute(): ServerConfigurationDomainModel {
        return serverConfigurationRepository.get()
    }

    // endregion

}