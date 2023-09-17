package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallLogEntryDataModel

internal class PhoneCallLogDataSourceImpl : PhoneCallLogDataSource {

    // region Fields

    private val logEntries: MutableStateFlow<List<PhoneCallLogEntryDataModel>> =
        MutableStateFlow(emptyList())

    // endregion

    // region PhoneCallLogDataSource implementation

    override fun add(entry: PhoneCallLogEntryDataModel) {
        logEntries.update { entries ->
            entries + entry
        }
    }

    override fun getAll(): List<PhoneCallLogEntryDataModel> {
        return logEntries.value
    }

    override fun getAllRx(): Flow<List<PhoneCallLogEntryDataModel>> {
        return logEntries
    }

    // endregion

}