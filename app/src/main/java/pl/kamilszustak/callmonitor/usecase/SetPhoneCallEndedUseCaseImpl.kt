package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.repository.PhoneCallRepository

class SetPhoneCallEndedUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : SetPhoneCallEndedUseCase {

    override suspend fun execute(phoneNumber: String, timestamp: Long) {
        phoneCallRepository.setEnded(phoneNumber, timestamp)
    }

}