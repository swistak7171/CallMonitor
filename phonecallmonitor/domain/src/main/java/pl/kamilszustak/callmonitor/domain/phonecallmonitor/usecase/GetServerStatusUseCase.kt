package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerStatusRepository

/**
 * A use case for retrieving the server current status.
 */
interface GetServerStatusUseCase {

    /**
     * Returns the server current status represented by [ServerStatusDomainModel].
     */
    suspend fun execute(): ServerStatusDomainModel
}

/**
 * A use case for retrieving the server current status.
 */
internal class GetServerStatusUseCaseImpl(
    private val serverStatusRepository: ServerStatusRepository,
) : GetServerStatusUseCase {

    // region GetServerStatusUseCase Implementation

    override suspend fun execute(): ServerStatusDomainModel {
        return serverStatusRepository.get()
    }

    // endregion

}