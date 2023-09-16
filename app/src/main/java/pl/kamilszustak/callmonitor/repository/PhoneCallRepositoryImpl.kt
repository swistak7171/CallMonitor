package pl.kamilszustak.callmonitor.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import logcat.LogPriority.WARN
import logcat.logcat
import pl.kamilszustak.callmonitor.datasource.ContactNameDataSource
import pl.kamilszustak.callmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallMetadataDataSource
import pl.kamilszustak.callmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.mapper.toOngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository

class PhoneCallRepositoryImpl(
    private val ongoingPhoneCallDataSource: OngoingPhoneCallDataSource,
    private val phoneCallLogDataSource: PhoneCallLogDataSource,
    private val contactNameDataSource: ContactNameDataSource,
    private val phoneCallMetadataDataSource: PhoneCallMetadataDataSource,
) : PhoneCallRepository {

    override suspend fun setStarted(state: PhoneCallEventDomainModel.PhoneCallStart) {
        val ongoingPhoneCall = state.toOngoingPhoneCallDataModel()
        ongoingPhoneCallDataSource.setStarted(ongoingPhoneCall)
    }

    override suspend fun setEnded(event: PhoneCallEventDomainModel.PhoneCallEnd) {
        val ongoingPhoneCall = ongoingPhoneCallDataSource.get()
        if (ongoingPhoneCall == null) {
            logcat(WARN) { "Cannot find an ongoing phone call" }
            return
        }

        if (ongoingPhoneCall.phoneNumber != event.phoneNumber) {
            logcat(WARN) { "The ongoing phone call has a different phone number than the ended one" }
            return
        }

        ongoingPhoneCallDataSource.setEnded()

        val logEntry = PhoneCallLogEntryDataModel(
            id = ongoingPhoneCall.id,
            startTimestamp = ongoingPhoneCall.startTimestamp,
            endTimestamp = event.timestamp,
            phoneNumber = event.phoneNumber,
        )
        phoneCallLogDataSource.add(logEntry)
    }

    override suspend fun getOngoing(): OngoingPhoneCallDomainModel? {
        val ongoingPhoneCall = ongoingPhoneCallDataSource.get()

        return if (ongoingPhoneCall != null) {
            phoneCallMetadataDataSource.incrementTimesQueried(ongoingPhoneCall.id)
            val contactName = contactNameDataSource.get(ongoingPhoneCall.phoneNumber)

            OngoingPhoneCallDomainModel(
                phoneNumber = ongoingPhoneCall.phoneNumber,
                contactName = contactName,
            )
        } else {
            null
        }
    }

    override suspend fun getAll(): List<PhoneCallLogEntryDomainModel> {
        return phoneCallLogDataSource.getAll()
            .toDomainModels()
    }

    override fun getAllRx(): Flow<List<PhoneCallLogEntryDomainModel>> {
        return phoneCallLogDataSource.getAllRx()
            .map { it.toDomainModels() }
    }

    private suspend fun List<PhoneCallLogEntryDataModel>.toDomainModels(): List<PhoneCallLogEntryDomainModel> {
        return map { entry ->
            val metadata = phoneCallMetadataDataSource.getOrCreate(entry.id)
            val contactName = contactNameDataSource.get(entry.phoneNumber)

            entry.toDomainModel(metadata, contactName)
        }
    }

}