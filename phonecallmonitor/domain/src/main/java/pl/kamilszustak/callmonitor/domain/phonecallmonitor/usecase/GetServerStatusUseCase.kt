package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerStatusRepository

interface GetServerStatusUseCase {
    fun execute(): ServerStatusDomainModel
}

internal class GetServerStatusUseCaseImpl(
    private val serverStatusRepository: ServerStatusRepository,
) : GetServerStatusUseCase {

    // region GetServerStatusUseCase Implementation

    override fun execute(): ServerStatusDomainModel {
        return serverStatusRepository.get()
    }

    // endregion

}