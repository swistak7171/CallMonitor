package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository

/**
 * A use case for retrieving all phone call log entries, i.e. phone call made or received by
 * the user.
 */
interface GetAllPhoneCallLogEntriesUseCase {

    /**
     * Returns a [List] of all phone call log entries represented by [PhoneCallLogEntryDomainModel].
     */
    suspend fun execute(): List<PhoneCallLogEntryDomainModel>

    /**
     * Returns a [Flow] of all phone call log entries represented by [PhoneCallLogEntryDomainModel].
     */
    fun executeRx(): Flow<List<PhoneCallLogEntryDomainModel>>
}

/**
 * A use case for getting all phone call log entries, i.e. phone call made or received by the user.
 */
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