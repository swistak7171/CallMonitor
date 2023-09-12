package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallStateDataModel

interface PhoneCallStateDataSource {
    fun getRx(): Flow<PhoneCallStateDataModel>
}