package pl.kamilszustak.callmonitor.repository

import pl.kamilszustak.callmonitor.datasource.ContactNameDataSource
import pl.kamilszustak.callmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntry
import kotlin.time.Duration.Companion.seconds

class PhoneCallRepositoryImpl(
    private val ongoingPhoneCallDataSource: OngoingPhoneCallDataSource,
    private val phoneCallLogDataSource: PhoneCallLogDataSource,
    private val contactNameDataSource: ContactNameDataSource,
) : PhoneCallRepository {

    override suspend fun setStarted(phoneNumber: String) {
        ongoingPhoneCallDataSource.setStarted(phoneNumber)
    }

    override suspend fun setEnded(phoneNumber: String) {
        val ongoingCallPhoneNumber = ongoingPhoneCallDataSource.get()
        if (phoneNumber != ongoingCallPhoneNumber) {
            return
        }

        ongoingPhoneCallDataSource.setEnded()

        val contactName = contactNameDataSource.get(phoneNumber)
        val logEntry = PhoneCallLogEntry(
            beginningTimestamp = System.currentTimeMillis(),
            duration = 10.seconds,
            phoneNumber = phoneNumber,
            contactName = contactName
        )
        phoneCallLogDataSource.add(logEntry)
    }

}