package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallLogEntryDataModel

interface PhoneCallLogDataSource {
    fun add(entry: PhoneCallLogEntryDataModel)
    fun getAll(): List<PhoneCallLogEntryDataModel>
    fun getAllRx(): Flow<List<PhoneCallLogEntryDataModel>>
}