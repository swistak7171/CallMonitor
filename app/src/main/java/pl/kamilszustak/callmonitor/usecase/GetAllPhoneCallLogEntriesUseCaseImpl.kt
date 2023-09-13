package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.repository.PhoneCallRepository

class GetAllPhoneCallLogEntriesUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : GetAllPhoneCallLogEntriesUseCase {

    override suspend fun execute(): List<PhoneCallLogEntryDomainModel> {
        return phoneCallRepository.getAll()
    }

    override fun executeRx(): Flow<List<PhoneCallLogEntryDomainModel>> {
        return phoneCallRepository.getAllRx()
    }

}