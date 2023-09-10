package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallState
import pl.kamilszustak.callmonitor.repository.PhoneCallStateRepository

class GetPhoneCallStateUseCaseImpl(
    private val phoneCallStateRepository: PhoneCallStateRepository,
) : GetPhoneCallStateUseCase {

    override fun executeRx(): Flow<PhoneCallState> {
        return phoneCallStateRepository.getRx()
    }

}