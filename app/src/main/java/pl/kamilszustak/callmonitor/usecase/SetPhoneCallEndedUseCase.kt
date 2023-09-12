package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel

interface SetPhoneCallEndedUseCase {
    suspend fun execute(state: PhoneCallStateDomainModel.EndedPhoneCall)
}