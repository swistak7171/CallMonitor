package pl.kamilszustak.callmonitor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pl.kamilszustak.callmonitor.model.LogEntryViewState
import pl.kamilszustak.callmonitor.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val requiredPermissions: Array<String> = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.READ_CONTACTS,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arePermissionsGranted = requiredPermissions
            .map { ContextCompat.checkSelfPermission(this, it) }
            .all { it == PackageManager.PERMISSION_GRANTED }

        val requestMultiplePermissionsLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                val allGranted = permissions.values.all { it }
                if (allGranted) {
                    val serviceIntent = Intent(application, PhoneCallMonitorService::class.java)
                    ContextCompat.startForegroundService(application, serviceIntent)
                } else {
                    Toast.makeText(
                        this,
                        "Required permissions not granted, phone call monitoring cannot be started",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

        if (!arePermissionsGranted) {
            requestMultiplePermissionsLauncher.launch(requiredPermissions)
        }

        setContent {
            MyApplicationTheme {
                val viewModel = koinViewModel<MainViewModel>()
                val state by viewModel.viewState.collectAsStateWithLifecycle()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PhoneCallLog(state.logEntries)
                }
            }
        }
    }
}

@Composable
private fun PhoneCallLog(logEntries: List<LogEntryViewState>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
            .height(64.dp)
            .padding(16.dp)
    ) {
        Text(
            text = logEntry.text,
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