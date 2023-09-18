package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository

/**
 * Use case for retrieving the currently ongoing phone call.
 */
interface GetOngoingPhoneCallUseCase {

    /**
     * Returns the currently ongoing phone call represented by [OngoingPhoneCallDomainModel].
     * If there is no ongoing phone call, then `null` is returned.
     */
    suspend fun execute(): OngoingPhoneCallDomainModel?
}

/**
 * Use case that gets the currently ongoing phone call.
 */
internal class GetOngoingPhoneCallUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : GetOngoingPhoneCallUseCase {

    // region GetOngoingPhoneCallUseCase Implementation

    override suspend fun execute(): OngoingPhoneCallDomainModel? {
        return phoneCallRepository.getOngoing()
    }

    // endregion

}