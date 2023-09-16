package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository

interface GetAllPhoneCallLogEntriesUseCase {
    suspend fun execute(): List<PhoneCallLogEntryDomainModel>
    fun executeRx(): Flow<List<PhoneCallLogEntryDomainModel>>
}

internal class GetAllPhoneCallLogEntriesUseCaseImpl(
    private val phoneCallRepository: PhoneCallRepository,
) : GetAllPhoneCallLogEntriesUseCase {

    // region GetAllPhoneCallLogEntriesUseCase Implementation

    override suspend fun execute(): List<PhoneCallLogEntryDomainModel> {
        return phoneCallRepository.getAll()
    }

    override fun executeRx(): Flow<List<PhoneCallLogEntryDomainModel>> {
        return phoneCallRepository.getAllRx()
    }

    // endregion

}