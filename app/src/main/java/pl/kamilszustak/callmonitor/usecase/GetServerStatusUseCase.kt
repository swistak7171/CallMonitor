package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.ServerStatusDomainModel

interface GetServerStatusUseCase {
    fun execute(): ServerStatusDomainModel
}