package pl.kamilszustak.callmonitor.usecase

import kotlinx.datetime.Instant

interface SetPhoneCallEndedUseCase {
    suspend fun execute(phoneNumber: String, timestamp: Instant)
}