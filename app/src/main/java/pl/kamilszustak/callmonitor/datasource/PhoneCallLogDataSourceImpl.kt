package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntry

class PhoneCallLogDataSourceImpl : PhoneCallLogDataSource {

    private val logEntries: MutableStateFlow<List<PhoneCallLogEntry>> =
        MutableStateFlow(emptyList())

    override fun add(entry: PhoneCallLogEntry) {
        logEntries.update { entries ->
            entries + entry
        }
    }

    override fun getAll(): List<PhoneCallLogEntry> {
        return logEntries.value
    }

    override fun getAllRx(): Flow<List<PhoneCallLogEntry>> {
        return logEntries
    }

}