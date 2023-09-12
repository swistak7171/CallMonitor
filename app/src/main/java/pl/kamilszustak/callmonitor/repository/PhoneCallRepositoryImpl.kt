package pl.kamilszustak.callmonitor.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.kamilszustak.callmonitor.datasource.ContactNameDataSource
import pl.kamilszustak.callmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.datasource.PhoneCallLogDataSource
import pl.kamilszustak.callmonitor.mapper.toOngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallStateDomainModel

class PhoneCallRepositoryImpl(
    private val ongoingPhoneCallDataSource: OngoingPhoneCallDataSource,
    private val phoneCallLogDataSource: PhoneCallLogDataSource,
    private val contactNameDataSource: ContactNameDataSource,
) : PhoneCallRepository {

    override suspend fun setStarted(state: PhoneCallStateDomainModel.StartedPhoneCall) {
        val ongoingPhoneCall = state.toOngoingPhoneCallDataModel()
        ongoingPhoneCallDataSource.setStarted(ongoingPhoneCall)
    }

    override suspend fun setEnded(state: PhoneCallStateDomainModel.EndedPhoneCall) {
        val ongoingPhoneCall = ongoingPhoneCallDataSource.get()
        if (ongoingPhoneCall == null) {
            Log.w(PhoneCallRepositoryImpl::class.qualifiedName, "Cannot find an ongoing phone call")
            return
        }

        if (ongoingPhoneCall.phoneNumber != state.phoneNumber) {
            Log.w(
                PhoneCallRepositoryImpl::class.qualifiedName,
                "The ongoing phone call has a different phone number than the ended one"
            )
            return
        }

        ongoingPhoneCallDataSource.setEnded()

        val logEntry = PhoneCallLogEntryDataModel(
            id = ongoingPhoneCall.id,
            startTimestamp = ongoingPhoneCall.timestamp,
            endTimestamp = state.timestamp,
            phoneNumber = state.phoneNumber,
        )
        phoneCallLogDataSource.add(logEntry)
    }

    override suspend fun getOngoing(): OngoingPhoneCallDomainModel? {
        val ongoingPhoneCall = ongoingPhoneCallDataSource.get()

        return if (ongoingPhoneCall != null) {
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
        return phoneCallLogDataSource.getAll().map { entry ->
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
        return phoneCallLogDataSource.getAllRx().map { entries ->
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