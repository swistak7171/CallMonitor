@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeUiApi::class)

package pl.kamilszustak.callmonitor.ui.phonecallmonitor.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogEntryViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.viewmodel.PhoneCallLogViewModel
import pl.kamilszustak.callmonitor.ui.phonecallmonitor.R
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

/**
 * A [Composable] screen that displays the phone call log and the server information. It has two
 * states: loading and success. The loading state is displayed when the data is being loaded and the
 * success state is displayed when the data has been loaded successfully.
 */
@Composable
fun PhoneCallLogScreen(
    viewModel: PhoneCallLogViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    Content(state)
}

@Composable
private fun Content(state: PhoneCallLogViewState) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .semantics {
                testTagsAsResourceId = true
            },
        color = MaterialTheme.colorScheme.background
    ) {
        when (state) {
            is PhoneCallLogViewState.Loading -> {
                LoadingContent()
            }

            is PhoneCallLogViewState.Success -> {
                SuccessContent(state)
            }
        }
    }
}

/**
 * A [Composable] screen content that is displayed when the data is being loaded.
 */
@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.testTag("loading_indicator")
        )
    }
}

/**
 * A [Composable] screen content that is displayed when the data has been loaded successfully.
 */
@Composable
private fun SuccessContent(state: PhoneCallLogViewState.Success) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ServerInformation(
            serverHost = state.serverHost,
            serverPort = state.serverPort
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        PhoneCallLog(state.entries)
    }
}

/**
 * A [Composable] component that displays the server information based on the given [serverHost]
 * and [serverPort].
 */
@Composable
private fun ServerInformation(serverHost: String, serverPort: Int) {
    Card(
        modifier = Modifier
            .testTag("server_information_card")
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Wifi,
                    contentDescription = stringResource(R.string.phone_call_log_screen_server_information_icon_content_description)
                )

                Spacer(
                    modifier = Modifier.width(16.dp)
                )

                Text(
                    text = stringResource(R.string.phone_call_log_screen_server_information_header),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            Text(
                text = stringResource(R.string.phone_call_log_screen_server_host, serverHost),
                modifier = Modifier
                    .testTag("server_host_text")
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(R.string.phone_call_log_screen_server_port, serverPort),
                modifier = Modifier
                    .testTag("server_port_text")
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

/**
 * A [Composable] component that displays the phone call log based on the given [logEntries].
 */
@Composable
private fun PhoneCallLog(logEntries: List<PhoneCallLogEntryViewState>) {
    Card(
        modifier = Modifier
            .testTag("phone_call_log_card")
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Call,
                    contentDescription = stringResource(R.string.phone_call_log_screen_phone_call_log_icon_content_description)
                )

                Spacer(
                    modifier = Modifier.width(16.dp)
                )

                Text(
                    text = stringResource(R.string.phone_call_log_screen_phone_call_log_header),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }

            if (logEntries.isEmpty()) {
                Text(
                    text = stringResource(R.string.phone_call_log_screen_phone_call_log_empty),
                    modifier = Modifier
                        .testTag("empty_phone_call_log_text")
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    modifier = Modifier.testTag("phone_call_log")
                ) {
                    items(
                        items = logEntries,
                        key = { it.id }
                    ) { logEntry ->
                        PhoneCallLogEntry(logEntry)
                    }
                }
            }
        }
    }
}

/**
 * A [Composable] component that displays a single phone call log entry based on the given
 * [logEntry].
 */
@Composable
private fun PhoneCallLogEntry(logEntry: PhoneCallLogEntryViewState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Rounded.Person,
            contentDescription = stringResource(R.string.phone_call_log_screen_phone_call_log_entry_icon_content_description),
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = phoneCallLogEntryIconBackgroundColors.random(),
                    shape = RoundedCornerShape(50)
                )
                .padding(4.dp),
            tint = MaterialTheme.colorScheme.background
        )

        Spacer(
            modifier = Modifier.width(16.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = logEntry.contactName ?: logEntry.phoneNumber,
                modifier = Modifier.testTag("phone_call_log_entry_primary_text_${logEntry.id}"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge
            )

            if (logEntry.contactName != null) {
                Text(
                    text = logEntry.phoneNumber,
                    modifier = Modifier.testTag("phone_call_log_entry_secondary_text_${logEntry.id}"),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(
            modifier = Modifier.width(16.dp)
        )

        Text(
            text = logEntry.duration.toString(),
            modifier = Modifier.testTag("phone_call_log_entry_duration_text_${logEntry.id}"),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun LoadingContent_Preview() {
    val state = PhoneCallLogViewState.Loading

    Content(state)
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun SuccessContent_Preview() {
    val state = PhoneCallLogViewState.Success(
        serverHost = "123.123.123.123",
        serverPort = 1234,
        entries = listOf(
            PhoneCallLogEntryViewState(
                id = "1",
                phoneNumber = "123456789",
                contactName = "John Smith",
                duration = 50.seconds
            ),
            PhoneCallLogEntryViewState(
                id = "2",
                phoneNumber = "987654321",
                contactName = null,
                duration = 30.seconds
            ),
            PhoneCallLogEntryViewState(
                id = "3",
                phoneNumber = "123456789",
                contactName = null,
                duration = 2.minutes
            ),
            PhoneCallLogEntryViewState(
                id = "4",
                phoneNumber = "987654321",
                contactName = "Anna Smith",
                duration = 75.minutes
            ),
        )
    )

    SuccessContent(state)
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun SuccessContent_EmptyPhoneCallLog_Preview() {
    val state = PhoneCallLogViewState.Success(
        serverHost = "123.123.123.123",
        serverPort = 1234,
        entries = emptyList()
    )

    SuccessContent(state)
}

