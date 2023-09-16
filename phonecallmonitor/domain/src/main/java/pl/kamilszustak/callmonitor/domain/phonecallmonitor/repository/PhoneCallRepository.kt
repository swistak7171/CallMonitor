package pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel

interface PhoneCallRepository {
    suspend fun setStarted(event: PhoneCallEventDomainModel.PhoneCallStart)
    suspend fun setEnded(event: PhoneCallEventDomainModel.PhoneCallEnd)
    suspend fun getOngoing(): OngoingPhoneCallDomainModel?
    suspend fun getAll(): List<PhoneCallLogEntryDomainModel>
    fun getAllRx(): Flow<List<PhoneCallLogEntryDomainModel>>
}