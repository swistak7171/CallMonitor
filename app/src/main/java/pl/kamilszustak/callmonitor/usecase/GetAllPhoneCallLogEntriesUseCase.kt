package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDomainModel

interface GetAllPhoneCallLogEntriesUseCase {
    suspend fun execute(): List<PhoneCallLogEntryDomainModel>
    fun executeRx(): Flow<List<PhoneCallLogEntryDomainModel>>
}