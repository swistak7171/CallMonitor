package pl.kamilszustak.callmonitor.usecase

import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository

class SetPhoneCallStartedUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : SetPhoneCallStartedUseCase {

    override suspend fun execute(phoneNumber: String, timestamp: Instant) {
        phoneCallRepository.setStarted(phoneNumber, timestamp)
    }

}