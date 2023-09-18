package pl.kamilszustak.callmonitor.server.mapper

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.server.model.PhoneCallLogEntryRemoteResponseModel

/**
 * Maps a [PhoneCallLogEntryDomainModel] instance to a [PhoneCallLogEntryRemoteResponseModel]
 * instance.
 */
internal fun PhoneCallLogEntryDomainModel.toRemoteResponseModel(): PhoneCallLogEntryRemoteResponseModel {
    return PhoneCallLogEntryRemoteResponseModel(
        beginningTimestamp = startTimestamp,
        duration = duration.inWholeSeconds.toInt(),
        phoneNumber = phoneNumber,
        contactName = contactName,
        timesQueried = metadata.timesQueried
    )
}