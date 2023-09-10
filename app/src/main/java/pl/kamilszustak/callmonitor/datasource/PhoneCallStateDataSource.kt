package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallState

interface PhoneCallStateDataSource {
    fun getRx(): Flow<PhoneCallState>
}