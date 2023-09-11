package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDataModel

interface PhoneCallLogDataSource {
    fun add(entry: PhoneCallLogEntryDataModel)
    fun getAll(): List<PhoneCallLogEntryDataModel>
    fun getAllRx(): Flow<List<PhoneCallLogEntryDataModel>>
}