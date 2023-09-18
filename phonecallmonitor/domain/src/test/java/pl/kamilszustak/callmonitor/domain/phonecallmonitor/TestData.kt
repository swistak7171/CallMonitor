package pl.kamilszustak.callmonitor.domain.phonecallmonitor

import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallMetadataDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * This file contains data models used for testing purposes.
 */

const val id: String = "eee144a0-553c-11ee-8c99-0242ac120002"
val startTimestamp: Instant = Instant.fromEpochSeconds(10)
val endTimestamp: Instant = Instant.fromEpochSeconds(20)
val duration: Duration = 10.seconds
const val phoneNumber: String = "123456789"
const val contactName: String = "John Smith"

val phoneCallStartEventDomainModel: PhoneCallEventDomainModel.PhoneCallStart =
    PhoneCallEventDomainModel.PhoneCallStart(
        id = id,
        timestamp = startTimestamp,
        phoneNumber = phoneNumber
    )
val phoneCallEndEventDomainModel: PhoneCallEventDomainModel.PhoneCallEnd =
    PhoneCallEventDomainModel.PhoneCallEnd(
        timestamp = endTimestamp,
        phoneNumber = phoneNumber
    )

val ongoingPhoneCallDomainModel: OngoingPhoneCallDomainModel = OngoingPhoneCallDomainModel(
    id = id,
    phoneNumber = phoneNumber,
    contactName = contactName
)

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

val serverConfigurationDomainModel: ServerConfigurationDomainModel = ServerConfigurationDomainModel(
    host = "123.123.123.123",
    port = 9090
)