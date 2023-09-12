package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository

class GetOngoingPhoneCallUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : GetOngoingPhoneCallUseCase {

    override suspend fun execute(): OngoingPhoneCallDomainModel? {
        return phoneCallRepository.getOngoing()
    }

}