package pl.kamilszustak.callmonitor.data.phonecallmonitor

import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallEventDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerConfigurationDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallMetadataDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel
import java.util.UUID

val id: UUID = UUID.randomUUID()
val startTimestamp: Instant = Instant.fromEpochSeconds(100)
val endTimestamp: Instant = Instant.fromEpochSeconds(200)
const val phoneNumber: String = "123456789"
const val unknownPhoneNumber: String = "987654321"
const val contactName: String = "John Smith"
const val timesQueried: Int = 3

val phoneCallStartEventDataModel: PhoneCallEventDataModel.PhoneCallStart =
    PhoneCallEventDataModel.PhoneCallStart(
        id = id,
        timestamp = startTimestamp,
        phoneNumber = phoneNumber
    )
val phoneCallEndEventDataModel: PhoneCallEventDataModel.PhoneCallEnd =
    PhoneCallEventDataModel.PhoneCallEnd(
        timestamp = endTimestamp,
        phoneNumber = phoneNumber
    )
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
val phoneCallEndEventDomainModelWithUnknownPhoneNumber: PhoneCallEventDomainModel.PhoneCallEnd =
    PhoneCallEventDomainModel.PhoneCallEnd(
        timestamp = endTimestamp,
        phoneNumber = unknownPhoneNumber
    )

val ongoingPhoneCallDataModel: OngoingPhoneCallDataModel = OngoingPhoneCallDataModel(
    id = id,
    startTimestamp = startTimestamp,
    phoneNumber = phoneNumber
)
val ongoingPhoneCallDomainModel: OngoingPhoneCallDomainModel = OngoingPhoneCallDomainModel(
    id = id,
    phoneNumber = phoneNumber,
    contactName = contactName
)

val phoneCallMetadataDataModel: PhoneCallMetadataDataModel = PhoneCallMetadataDataModel(
    id = id,
    timesQueried = timesQueried
)
val phoneCallMetadataDomainModel: PhoneCallMetadataDomainModel =
    PhoneCallMetadataDomainModel(
        timesQueried = timesQueried
    )

val phoneCallLogEntryDataModel: PhoneCallLogEntryDataModel = PhoneCallLogEntryDataModel(
    id = id,
    startTimestamp = startTimestamp,
    endTimestamp = endTimestamp,
    phoneNumber = phoneNumber
)
val phoneCallLogEntryDomainModel: PhoneCallLogEntryDomainModel =
    PhoneCallLogEntryDomainModel(
        id = id,
        metadata = phoneCallMetadataDomainModel,
        startTimestamp = startTimestamp,
        endTimestamp = endTimestamp,
        phoneNumber = phoneNumber,
        contactName = contactName,
    )

const val serverHost: String = "123.123.123.123"
const val serverPort: Int = 9090
val serverStartTimestamp: Instant = Instant.fromEpochSeconds(20)

val serverConfigurationDataModel: ServerConfigurationDataModel = ServerConfigurationDataModel(
    host = serverHost,
    port = serverPort
)
val serverConfigurationDomainModel: ServerConfigurationDomainModel = ServerConfigurationDomainModel(
    host = serverHost,
    port = serverPort
)

val serverStatusRunningDataModel: ServerStatusDataModel.Running = ServerStatusDataModel.Running(
    startTimestamp = serverStartTimestamp
)
val serverStatusRunningDomainModel: ServerStatusDomainModel.Running = ServerStatusDomainModel.Running(
    startTimestamp = serverStartTimestamp
)