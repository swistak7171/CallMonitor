package pl.kamilszustak.callmonitor.presentation.phonecallmonitor

import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallMetadataDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogEntryViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogViewState
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * This file contains data models used for testing purposes.
 */

val serverConfigurationDomainModel: ServerConfigurationDomainModel = ServerConfigurationDomainModel(
    host = "123.123.123.123",
    port = 9090
)

const val id: String = "eee144a0-553c-11ee-8c99-0242ac120002"
val startTimestamp: Instant = Instant.fromEpochSeconds(10)
val endTimestamp: Instant = Instant.fromEpochSeconds(20)
val duration: Duration = 10.seconds
const val phoneNumber: String = "123456789"
const val contactName: String = "John Smith"

val phoneCallLogEntryDomainModel: PhoneCallLogEntryDomainModel = PhoneCallLogEntryDomainModel(
    id = id,
    metadata = PhoneCallMetadataDomainModel(
        timesQueried = 0
    ),
    startTimestamp = startTimestamp,
    endTimestamp = endTimestamp,
    phoneNumber = phoneNumber,
    contactName = contactName
)
val phoneCallLogEntryDomainModelWithoutContactName: PhoneCallLogEntryDomainModel =
    PhoneCallLogEntryDomainModel(
        id = id,
        metadata = PhoneCallMetadataDomainModel(
            timesQueried = 0
        ),
        startTimestamp = startTimestamp,
        endTimestamp = endTimestamp,
        phoneNumber = phoneNumber,
        contactName = null
    )

val phoneCallLogEntryViewState: PhoneCallLogEntryViewState = PhoneCallLogEntryViewState(
    id = id,
    phoneNumber = phoneNumber,
    contactName = contactName,
    duration = duration
)

val phoneCallLogSuccessViewState: PhoneCallLogViewState.Success = PhoneCallLogViewState.Success(
    serverHost = serverConfigurationDomainModel.host,
    serverPort = serverConfigurationDomainModel.port,
    entries = listOf(phoneCallLogEntryViewState)
)