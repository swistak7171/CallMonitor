package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallEventDataModel

interface PhoneCallEventRepository {
    fun getRx(): Flow<PhoneCallEventDataModel>
}