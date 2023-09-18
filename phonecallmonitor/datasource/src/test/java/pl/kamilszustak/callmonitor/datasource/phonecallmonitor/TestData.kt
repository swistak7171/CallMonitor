package pl.kamilszustak.callmonitor.datasource.phonecallmonitor

import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallEventDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerConfigurationDataModel

/**
 * This file contains data models used for testing purposes.
 */

const val id: String = "eee144a0-553c-11ee-8c99-0242ac120002"
const val phoneNumber: String = "123123123"
const val contactName: String = "John Smith"
val phoneCallStartTimestamp: Instant = Instant.fromEpochSeconds(1000)
val phoneCallEndTimestamp: Instant = Instant.fromEpochSeconds(2000)

val ongoingPhoneCallDataModel: OngoingPhoneCallDataModel = OngoingPhoneCallDataModel(
    id = id,
    startTimestamp = phoneCallStartTimestamp,
    phoneNumber = phoneNumber
)

val phoneCallLogEntryDataModel: PhoneCallLogEntryDataModel = PhoneCallLogEntryDataModel(
    id = id,
    startTimestamp = phoneCallStartTimestamp,
    endTimestamp = phoneCallEndTimestamp,
    phoneNumber = phoneNumber
)

val phoneCallStartEventDataModel: PhoneCallEventDataModel.PhoneCallStart =
    PhoneCallEventDataModel.PhoneCallStart(
        id = id,
        timestamp = phoneCallStartTimestamp,
        phoneNumber = phoneNumber
    )

val phoneCallEndEventDataModel: PhoneCallEventDataModel.PhoneCallEnd =
    PhoneCallEventDataModel.PhoneCallEnd(
        timestamp = phoneCallEndTimestamp,
        phoneNumber = phoneNumber
    )

val serverStartTimestamp: Instant = Instant.fromEpochSeconds(15)
const val serverHost: String = "123.123.123.123"
const val serverPort: Int = 8080

val serverConfigurationDataModel: ServerConfigurationDataModel = ServerConfigurationDataModel(
    host = serverHost,
    port = serverPort
)

val serverConfigurationDataModelWithDefaultHost: ServerConfigurationDataModel =
    ServerConfigurationDataModel(
        host = "0.0.0.0",
        port = serverPort
    )