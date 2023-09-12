package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.OngoingPhoneCallDomainModel

interface GetOngoingPhoneCallUseCase {
    suspend fun execute(): OngoingPhoneCallDomainModel?
}