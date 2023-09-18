package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerStatusRepository

/**
 * A use case for setting the server current status.
 */
interface SetServerStatusUseCase {

    /**
     * Sets the server current status. The status change [event] is represented by
     * [ServerStatusEventDomainModel].
     */
    suspend fun execute(event: ServerStatusEventDomainModel)
}

/**
 * A use case for setting the server current status.
 */
internal class SetServerStatusUseCaseImpl(
    private val serverStatusRepository: ServerStatusRepository,
) : SetServerStatusUseCase {

    // region SetServerStatusUseCase Implementation

    override suspend fun execute(event: ServerStatusEventDomainModel) {
        when (event) {
            is ServerStatusEventDomainModel.Started -> {
                serverStatusRepository.setStarted()
            }

            is ServerStatusEventDomainModel.Stopped -> {
                serverStatusRepository.setStopped()
            }
        }
    }

    // endregion

}