package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallEventDomainModel

interface GetPhoneCallEventUseCase {
    fun executeRx(): Flow<PhoneCallEventDomainModel>
}