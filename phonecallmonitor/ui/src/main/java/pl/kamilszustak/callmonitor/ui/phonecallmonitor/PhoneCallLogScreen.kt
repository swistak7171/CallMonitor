package pl.kamilszustak.callmonitor.ui.phonecallmonitor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.LogEntryViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.viewmodel.PhoneCallLogViewModel

@Composable
fun PhoneCallLogScreen() {
    val viewModel = koinViewModel<PhoneCallLogViewModel>()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            state.serverHost?.let { serverHost ->
                ServerHost(serverHost)
            }
            state.serverPort?.let { serverPort ->
                ServerPort(serverPort)
            }
            PhoneCallLog(state.logEntries)
        }
    }
}

@Composable
private fun ServerHost(host: String) {
    Text(
        text = "Server host: $host",
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun ServerPort(port: Int) {
    Text(
        text = "Server port: $port",
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun PhoneCallLog(logEntries: List<LogEntryViewState>) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Phone call log",
            style = MaterialTheme.typography.headlineLarge
        )

        LazyColumn {
            items(
                items = logEntries,
                key = { it.hashCode() }
            ) { logEntry ->
                PhoneCallLogEntry(logEntry)
            }
        }
    }
}

@Composable
private fun PhoneCallLogEntry(logEntry: LogEntryViewState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = logEntry.text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.width(32.dp)
        )

        Text(
            text = logEntry.duration.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}