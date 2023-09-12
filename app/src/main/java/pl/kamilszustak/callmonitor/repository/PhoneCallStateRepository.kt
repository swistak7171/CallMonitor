package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallStateDataModel

interface PhoneCallStateRepository {
    fun getRx(): Flow<PhoneCallStateDataModel>
}