package pl.kamilszustak.callmonitor.usecase

import pl.kamilszustak.callmonitor.repository.PhoneCallRepository

class SetPhoneCallStartedUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : SetPhoneCallStartedUseCase {

    override suspend fun execute(phoneNumber: String) {
        phoneCallRepository.setStarted(phoneNumber)
    }

}