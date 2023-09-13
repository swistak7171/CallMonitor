package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.ServerStatusEventDomainModel

interface SetServerStatusUseCase {
    fun execute(event: ServerStatusEventDomainModel)
}