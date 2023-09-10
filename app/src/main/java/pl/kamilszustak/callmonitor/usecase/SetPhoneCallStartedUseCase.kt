package pl.kamilszustak.callmonitor.usecase

interface SetPhoneCallStartedUseCase {
    suspend fun execute(phoneNumber: String)
}