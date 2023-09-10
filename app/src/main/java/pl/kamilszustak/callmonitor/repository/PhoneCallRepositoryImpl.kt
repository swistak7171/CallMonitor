package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.datasource.ContactNameDataSource
import pl.kamilszustak.callmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntry
import kotlin.time.Duration.Companion.milliseconds

class PhoneCallRepositoryImpl(
    private val ongoingPhoneCallDataSource: OngoingPhoneCallDataSource,
    private val phoneCallLogDataSource: PhoneCallLogDataSource,
    private val contactNameDataSource: ContactNameDataSource,
) : PhoneCallRepository {

    override suspend fun setStarted(phoneNumber: String, timestamp: Long) {
        ongoingPhoneCallDataSource.setStarted(phoneNumber, timestamp)
    }

    override suspend fun setEnded(phoneNumber: String, timestamp: Long) {
        val ongoingCallPhoneNumber = ongoingPhoneCallDataSource.get()
        if (ongoingCallPhoneNumber == null || phoneNumber != ongoingCallPhoneNumber.first) {
            return
        }

        ongoingPhoneCallDataSource.setEnded()

        val contactName = contactNameDataSource.get(phoneNumber)
        val logEntry = PhoneCallLogEntry(
            beginningTimestamp = System.currentTimeMillis(),
            duration = (timestamp - ongoingCallPhoneNumber.second).milliseconds,
            phoneNumber = phoneNumber,
            contactName = contactName
        )
        phoneCallLogDataSource.add(logEntry)
    }

    override suspend fun getAll(): List<PhoneCallLogEntry> {
        return phoneCallLogDataSource.getAll()
    }

    override fun getAllRx(): Flow<List<PhoneCallLogEntry>> {
        return phoneCallLogDataSource.getAllRx()
    }

}