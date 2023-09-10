package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntry

interface PhoneCallRepository {
    suspend fun setStarted(phoneNumber: String)
    suspend fun setEnded(phoneNumber: String)
    suspend fun getAll(): List<PhoneCallLogEntry>
    fun getAllRx(): Flow<List<PhoneCallLogEntry>>
}