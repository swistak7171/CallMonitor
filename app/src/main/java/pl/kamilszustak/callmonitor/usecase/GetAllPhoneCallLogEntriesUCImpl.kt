package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntry
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository

class GetAllPhoneCallLogEntriesUCImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : GetAllPhoneCallLogEntriesUC {

    override suspend fun execute(): List<PhoneCallLogEntry> {
        return phoneCallRepository.getAll()
    }

    override fun executeRx(): Flow<List<PhoneCallLogEntry>> {
        return phoneCallRepository.getAllRx()
    }

}