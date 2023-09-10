package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntry

interface PhoneCallLogDataSource {
    fun add(entry: PhoneCallLogEntry)
    fun getAll(): List<PhoneCallLogEntry>
    fun getAllRx(): Flow<List<PhoneCallLogEntry>>
}