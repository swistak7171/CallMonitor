package pl.kamilszustak.callmonitor.data.phonecallmonitor.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ContactNameDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallMetadataDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toOngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository
import pl.kamilszustak.callmonitor.logger.Logger

// region Constants

private const val TAG: String = "PhoneCallRepositoryImpl"

// endregion

/**
 * A repository for managing phone calls. It handles the current phone call events, as well as
 * the history of phone calls.
 */
internal class PhoneCallRepositoryImpl(
    private val logger: Logger,
    private val ongoingPhoneCallDataSource: OngoingPhoneCallDataSource,
    private val phoneCallLogDataSource: PhoneCallLogDataSource,
    private val contactNameDataSource: ContactNameDataSource,
    private val phoneCallMetadataDataSource: PhoneCallMetadataDataSource,
) : PhoneCallRepository {

    // region PhoneCallRepository Implementation

    override suspend fun setStarted(event: PhoneCallEventDomainModel.PhoneCallStart) {
        val ongoingPhoneCall = event.toOngoingPhoneCallDataModel()
        ongoingPhoneCallDataSource.setStarted(ongoingPhoneCall)
    }

    override suspend fun setEnded(event: PhoneCallEventDomainModel.PhoneCallEnd) {
        val ongoingPhoneCall = ongoingPhoneCallDataSource.get()
        if (ongoingPhoneCall == null) {
            logger.warn(TAG) { "Cannot find an ongoing phone call" }
            return
        }

        if (ongoingPhoneCall.phoneNumber != event.phoneNumber) {
            logger.warn(TAG) { "The ongoing phone call has a different phone number than the ended one" }
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

            ongoingPhoneCall.toDomainModel(contactName)
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

    // endregion

    // region Private Methods

    private suspend fun List<PhoneCallLogEntryDataModel>.toDomainModels(): List<PhoneCallLogEntryDomainModel> {
        return map { entry ->
            val metadata = phoneCallMetadataDataSource.get(entry.id)
            val contactName = contactNameDataSource.get(entry.phoneNumber)

            entry.toDomainModel(metadata, contactName)
        }
    }

    // endregion

}