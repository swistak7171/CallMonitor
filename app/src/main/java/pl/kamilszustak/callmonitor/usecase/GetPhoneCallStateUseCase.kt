package pl.kamilszustak.callmonitor.usecase

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallState

interface GetPhoneCallStateUseCase {
    fun executeRx(): Flow<PhoneCallState>
}