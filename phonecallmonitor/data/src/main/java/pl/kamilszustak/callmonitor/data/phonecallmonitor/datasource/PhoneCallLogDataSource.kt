package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallLogEntryDataModel

/**
 * A data source for managing a phone call log.
 */
interface PhoneCallLogDataSource {

    /**
     * Adds a phone call log [entry] to the log.
     */
    fun add(entry: PhoneCallLogEntryDataModel)

    /**
     * Returns a [List] of all phone call log entries represented by [PhoneCallLogEntryDataModel].
     */
    fun getAll(): List<PhoneCallLogEntryDataModel>

    /**
     * Returns a [Flow] of [List] of all phone call log entries represented by
     * [PhoneCallLogEntryDataModel].
     */
    fun getAllRx(): Flow<List<PhoneCallLogEntryDataModel>>
}