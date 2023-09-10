package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntry

interface GetAllPhoneCallLogEntriesUC {
    suspend fun execute(): List<PhoneCallLogEntry>
    fun executeRx(): Flow<List<PhoneCallLogEntry>>
}