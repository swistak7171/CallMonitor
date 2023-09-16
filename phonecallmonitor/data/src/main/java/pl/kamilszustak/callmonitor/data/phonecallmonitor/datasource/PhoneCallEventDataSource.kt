package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallEventDataModel

interface PhoneCallEventDataSource {
    fun getRx(): Flow<PhoneCallEventDataModel>
}