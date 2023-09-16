package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository

interface GetOngoingPhoneCallUseCase {
    suspend fun execute(): OngoingPhoneCallDomainModel?
}

internal class GetOngoingPhoneCallUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : GetOngoingPhoneCallUseCase {

    override suspend fun execute(): OngoingPhoneCallDomainModel? {
        return phoneCallRepository.getOngoing()
    }

}