package pl.kamilszustak.callmonitor.ui.phonecallmonitor

import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogEntryViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogViewState
import kotlin.time.Duration.Companion.minutes

const val serverHost: String = "123.123.123.123"
const val serverPort: Int = 9090

val phoneCallLogSuccessViewState: PhoneCallLogViewState.Success = PhoneCallLogViewState.Success(
    serverHost = serverHost,
    serverPort = serverPort,
    entries = listOf(
        PhoneCallLogEntryViewState(
            id = "11111",
            phoneNumber = "111222333",
            contactName = "John Smith",
            duration = 5.minutes
        ),
        PhoneCallLogEntryViewState(
            id = "22222",
            phoneNumber = "333222111",
            contactName = null,
            duration = 75.minutes
        ),
        PhoneCallLogEntryViewState(
            id = "33333",
            phoneNumber = "123456789",
            contactName = "Anna Smith",
            duration = 250.minutes
        )
    )
)

val phoneCallLogSuccessViewStateWithEmptyLog: PhoneCallLogViewState.Success =
    PhoneCallLogViewState.Success(
        serverHost = serverHost,
        serverPort = serverPort,
        entries = emptyList()
    )