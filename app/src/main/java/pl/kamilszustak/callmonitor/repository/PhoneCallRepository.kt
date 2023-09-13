package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDomainModel

interface PhoneCallRepository {
    suspend fun setStarted(event: PhoneCallEventDomainModel.PhoneCallStart)
    suspend fun setEnded(event: PhoneCallEventDomainModel.PhoneCallEnd)
    suspend fun getOngoing(): OngoingPhoneCallDomainModel?
    suspend fun getAll(): List<PhoneCallLogEntryDomainModel>
    fun getAllRx(): Flow<List<PhoneCallLogEntryDomainModel>>
}