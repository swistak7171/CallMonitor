package pl.kamilszustak.callmonitor.presentation.phonecallmonitor.mapper

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogViewState

internal fun PhoneCallLogEntryDomainModel.toViewState(): PhoneCallLogViewState.Success.PhoneCallLogEntryViewState =
    PhoneCallLogViewState.Success.PhoneCallLogEntryViewState(
        text = contactName ?: phoneNumber,
        duration = duration,
    )