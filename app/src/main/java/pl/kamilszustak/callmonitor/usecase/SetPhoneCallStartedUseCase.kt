package pl.kamilszustak.callmonitor.usecase

import kotlinx.datetime.Instant

interface SetPhoneCallStartedUseCase {
    suspend fun execute(phoneNumber: String, timestamp: Instant)
}