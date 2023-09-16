package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallEventRepository

interface GetPhoneCallEventUseCase {
    fun executeRx(): Flow<PhoneCallEventDomainModel>
}

internal class GetPhoneCallEventUseCaseImpl(
    private val phoneCallEventRepository: PhoneCallEventRepository,
) : GetPhoneCallEventUseCase {

    override fun executeRx(): Flow<PhoneCallEventDomainModel> {
        return phoneCallEventRepository.getRx()
    }

}