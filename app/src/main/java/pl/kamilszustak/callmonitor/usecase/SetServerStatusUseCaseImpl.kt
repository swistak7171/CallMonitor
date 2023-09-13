package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.ServerStatusEventDomainModel
import pl.kamilszustak.callmonitor.repository.ServerStatusRepository

class SetServerStatusUseCaseImpl(
    private val serverStatusRepository: ServerStatusRepository,
) : SetServerStatusUseCase {

    override fun execute(event: ServerStatusEventDomainModel) {
        when (event) {
            is ServerStatusEventDomainModel.Started -> {
                serverStatusRepository.setStarted()
            }

            is ServerStatusEventDomainModel.Stopped -> {
                serverStatusRepository.setStopped()
            }
        }
    }

}