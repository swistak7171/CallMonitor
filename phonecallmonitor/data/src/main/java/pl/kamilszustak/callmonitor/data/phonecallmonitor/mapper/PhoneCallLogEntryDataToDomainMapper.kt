package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel

/**
 * Maps a [PhoneCallLogEntryDataModel] instance to the [PhoneCallLogEntryDomainModel] instance with
 * the given [metadata] and [contactName].
 */
internal fun PhoneCallLogEntryDataModel.toDomainModel(
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