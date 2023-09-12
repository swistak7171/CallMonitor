package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository

class SetPhoneCallEndedUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : SetPhoneCallEndedUseCase {

    override suspend fun execute(state: PhoneCallStateDomainModel.EndedPhoneCall) {
        phoneCallRepository.setEnded(state)
    }

}