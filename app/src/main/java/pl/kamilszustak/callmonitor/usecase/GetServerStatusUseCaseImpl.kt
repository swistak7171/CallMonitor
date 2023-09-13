package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.ServerStatusDomainModel
import pl.kamilszustak.callmonitor.repository.ServerStatusRepository

class GetServerStatusUseCaseImpl(
    private val serverStatusRepository: ServerStatusRepository,
) : GetServerStatusUseCase {

    override fun execute(): ServerStatusDomainModel {
        return serverStatusRepository.get()
    }

}