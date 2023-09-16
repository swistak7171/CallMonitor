package pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel

interface PhoneCallEventRepository {
    fun getRx(): Flow<PhoneCallEventDomainModel>
}