package pl.kamilszustak.callmonitor.usecase

import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository

class SetPhoneCallEndedUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : SetPhoneCallEndedUseCase {

    override suspend fun execute(phoneNumber: String, timestamp: Instant) {
        phoneCallRepository.setEnded(phoneNumber, timestamp)
    }

}