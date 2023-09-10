package pl.kamilszustak.callmonitor.repository

interface PhoneCallRepository {
    suspend fun setStarted(phoneNumber: String)
    suspend fun setEnded(phoneNumber: String)
}