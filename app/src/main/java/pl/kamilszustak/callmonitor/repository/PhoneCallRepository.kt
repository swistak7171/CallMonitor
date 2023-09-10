package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntry

interface PhoneCallRepository {
    suspend fun setStarted(phoneNumber: String, timestamp: Long)
    suspend fun setEnded(phoneNumber: String, timestamp: Long)
    suspend fun getAll(): List<PhoneCallLogEntry>
    fun getAllRx(): Flow<List<PhoneCallLogEntry>>
}