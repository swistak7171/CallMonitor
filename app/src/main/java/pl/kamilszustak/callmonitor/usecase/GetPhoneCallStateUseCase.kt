package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel

interface GetPhoneCallStateUseCase {
    fun executeRx(): Flow<PhoneCallStateDomainModel>
}