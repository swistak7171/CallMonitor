package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallMetadataDataModel

fun PhoneCallLogEntryDataModel.toDomainModel(
    metadata: PhoneCallMetadataDataModel,
    contactName: String?
): PhoneCallLogEntryDomainModel {
    return PhoneCallLogEntryDomainModel(
        id = id,
        metadata = metadata.toDomainModel(),
        startTimestamp = startTimestamp,
        endTimestamp = endTimestamp,
        phoneNumber = phoneNumber,
        contactName = contactName
    )
}