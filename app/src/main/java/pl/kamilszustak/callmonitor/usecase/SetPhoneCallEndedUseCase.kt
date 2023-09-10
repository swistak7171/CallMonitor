package pl.kamilszustak.callmonitor.usecase

interface SetPhoneCallEndedUseCase {
    suspend fun execute(phoneNumber: String, timestamp: Long)
}