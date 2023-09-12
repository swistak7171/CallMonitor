package pl.kamilszustak.callmonitor.mapper

import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.model.PhoneCallLogEntryRemoteModel

fun PhoneCallLogEntryDomainModel.toRemoteModel(): PhoneCallLogEntryRemoteModel =
    PhoneCallLogEntryRemoteModel(
        beginningTimestamp = startTimestamp,
        duration = duration.inWholeSeconds.toInt(),
        phoneNumber = phoneNumber,
        contactName = contactName,
        timesQueried = metadata.timesQueried
    )