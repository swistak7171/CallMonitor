package pl.kamilszustak.callmonitor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}