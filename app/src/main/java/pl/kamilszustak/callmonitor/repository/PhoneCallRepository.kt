package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel

interface PhoneCallRepository {
    suspend fun setStarted(state: PhoneCallStateDomainModel.StartedPhoneCall)
    suspend fun setEnded(state: PhoneCallStateDomainModel.EndedPhoneCall)
    suspend fun getOngoing(): OngoingPhoneCallDomainModel?
    suspend fun getAll(): List<PhoneCallLogEntryDomainModel>
    fun getAllRx(): Flow<List<PhoneCallLogEntryDomainModel>>
}