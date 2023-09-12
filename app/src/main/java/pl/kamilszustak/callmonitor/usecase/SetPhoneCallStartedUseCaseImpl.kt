package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository

class SetPhoneCallStartedUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : SetPhoneCallStartedUseCase {

    override suspend fun execute(state: PhoneCallStateDomainModel.StartedPhoneCall) {
        phoneCallRepository.setStarted(state)
    }

}