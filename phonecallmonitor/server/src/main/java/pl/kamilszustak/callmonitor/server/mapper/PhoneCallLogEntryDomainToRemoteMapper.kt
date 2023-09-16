package pl.kamilszustak.callmonitor.server.mapper

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.server.model.PhoneCallLogEntryRemoteModel

fun PhoneCallLogEntryDomainModel.toRemoteModel(): PhoneCallLogEntryRemoteModel {
    return PhoneCallLogEntryRemoteModel(
        beginningTimestamp = startTimestamp,
        duration = duration.inWholeSeconds.toInt(),
        phoneNumber = phoneNumber,
        contactName = contactName,
        timesQueried = metadata.timesQueried
    )
}