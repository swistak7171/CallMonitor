package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel

interface SetPhoneCallStartedUseCase {
    suspend fun execute(state: PhoneCallStateDomainModel.StartedPhoneCall)
}