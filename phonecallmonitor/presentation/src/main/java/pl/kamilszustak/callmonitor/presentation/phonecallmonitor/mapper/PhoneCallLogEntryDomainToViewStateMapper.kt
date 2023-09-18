package pl.kamilszustak.callmonitor.presentation.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogEntryViewState

/**
 * Maps a [PhoneCallLogEntryDomainModel] instance to a [PhoneCallLogEntryViewState] instance.
 */
internal fun PhoneCallLogEntryDomainModel.toViewState(): PhoneCallLogEntryViewState =
    PhoneCallLogEntryViewState(
        id = id,
        phoneNumber = phoneNumber,
        contactName = contactName,
        duration = duration,
    )