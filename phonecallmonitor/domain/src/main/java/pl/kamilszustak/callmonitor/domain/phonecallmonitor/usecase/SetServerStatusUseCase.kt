package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerStatusRepository

interface SetServerStatusUseCase {
    suspend fun execute(event: ServerStatusEventDomainModel)
}

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