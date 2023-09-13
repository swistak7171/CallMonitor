package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallEventDataModel

interface PhoneCallEventDataSource {
    fun getRx(): Flow<PhoneCallEventDataModel>
}