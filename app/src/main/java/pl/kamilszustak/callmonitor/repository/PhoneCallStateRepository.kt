package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallState

interface PhoneCallStateRepository {
    fun getRx(): Flow<PhoneCallState>
}