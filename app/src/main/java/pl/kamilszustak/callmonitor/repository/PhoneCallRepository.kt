package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDomainModel

interface PhoneCallRepository {
    suspend fun setStarted(phoneNumber: String, timestamp: Instant)
    suspend fun setEnded(phoneNumber: String, timestamp: Instant)
    suspend fun getAll(): List<PhoneCallLogEntryDomainModel>
    fun getAllRx(): Flow<List<PhoneCallLogEntryDomainModel>>
}