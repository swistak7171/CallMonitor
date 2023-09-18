package pl.kamilszustak.callmonitor.server.phonecallmonitor

import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallMetadataDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel
import pl.kamilszustak.callmonitor.server.model.OngoingPhoneCallRemoteResponseModel
import pl.kamilszustak.callmonitor.server.model.PhoneCallLogEntryRemoteResponseModel
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

val ongoingPhoneCallDomainModel: OngoingPhoneCallDomainModel = OngoingPhoneCallDomainModel(
    id = id,
    phoneNumber = phoneNumber,
    contactName = contactName
)
internal val ongoingPhoneCallRemoteResponseModel: OngoingPhoneCallRemoteResponseModel =
    OngoingPhoneCallRemoteResponseModel(
        phoneNumber = phoneNumber,
        contactName = contactName
    )
const val ongoingPhoneCallJsonResponse: String =
    """{"number":"123456789","name":"John Smith","ongoing":true}"""

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
internal val phoneCallLogEntryRemoteResponseModel: PhoneCallLogEntryRemoteResponseModel =
    PhoneCallLogEntryRemoteResponseModel(
        beginningTimestamp = startTimestamp,
        duration = duration.inWholeSeconds.toInt(),
        phoneNumber = phoneNumber,
        contactName = contactName,
        timesQueried = 0
    )
const val phoneCallLogJsonResponse: String =
    """[{"beginning":"1970-01-01T00:00:10Z","duration":10,"number":"123456789","name":"John Smith","timesQueried":0}]"""
const val emptyPhoneCallLogJsonResponse: String = """[]"""

val serverConfigurationDomainModel: ServerConfigurationDomainModel = ServerConfigurationDomainModel(
    host = "123.123.123.123",
    port = 9090
)
val serverStatusRunningDomainModel: ServerStatusDomainModel.Running =
    ServerStatusDomainModel.Running(
        startTimestamp = startTimestamp
    )

val testRoutes: List<String> = listOf("/test-route-1", "/test-route-2")
const val serverStatusJsonResponse: String = """{"start":"1970-01-01T00:00:10Z","services":[{"name":"test-route-1","uri":"http://123.123.123.123:9090/test-route-1"},{"name":"test-route-2","uri":"http://123.123.123.123:9090/test-route-2"}]}"""