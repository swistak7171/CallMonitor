package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.datasource.ContactNameDataSource
import pl.kamilszustak.callmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDomainModel
import java.util.UUID

class PhoneCallRepositoryImpl(
    private val ongoingPhoneCallDataSource: OngoingPhoneCallDataSource,
    private val phoneCallLogDataSource: PhoneCallLogDataSource,
    private val contactNameDataSource: ContactNameDataSource,
) : PhoneCallRepository {

    override suspend fun setStarted(phoneNumber: String, timestamp: Instant) {
        ongoingPhoneCallDataSource.setStarted(phoneNumber, timestamp)
    }

    override suspend fun setEnded(phoneNumber: String, timestamp: Instant) {
        val ongoingCallPhoneNumber = ongoingPhoneCallDataSource.get()
        if (ongoingCallPhoneNumber == null || phoneNumber != ongoingCallPhoneNumber.first) {
            return
        }

        ongoingPhoneCallDataSource.setEnded()

        val logEntry = PhoneCallLogEntryDataModel(
            id = UUID.randomUUID(),
            startTimestamp = ongoingCallPhoneNumber.second,
            endTimestamp = timestamp,
            phoneNumber = phoneNumber,
        )
        phoneCallLogDataSource.add(logEntry)
    }

    override suspend fun getAll(): List<PhoneCallLogEntryDomainModel> {
        return phoneCallLogDataSource.getAll()
            .map { entry ->
                val contactName = contactNameDataSource.get(entry.phoneNumber)

                PhoneCallLogEntryDomainModel(
                    id = entry.id,
                    startTimestamp = entry.startTimestamp,
                    endTimestamp = entry.endTimestamp,
                    phoneNumber = entry.phoneNumber,
                    contactName = contactName,
                )
            }
    }

    override fun getAllRx(): Flow<List<PhoneCallLogEntryDomainModel>> {
        return phoneCallLogDataSource.getAllRx()
            .map { entries ->
                entries.map { entry ->
                    val contactName = contactNameDataSource.get(entry.phoneNumber)

                    PhoneCallLogEntryDomainModel(
                        id = entry.id,
                        startTimestamp = entry.startTimestamp,
                        endTimestamp = entry.endTimestamp,
                        phoneNumber = entry.phoneNumber,
                        contactName = contactName,
                    )
                }
            }
    }

}