package pl.kamilszustak.callmonitor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import pl.kamilszustak.callmonitor.ui.phonecallmonitor.view.PhoneCallLogScreen
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
                    startPhoneCallMonitorService()
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
        } else {
            startPhoneCallMonitorService()
        }

        setContent {
            MyApplicationTheme {
                PhoneCallLogScreen()
            }
        }
    }

    private fun startPhoneCallMonitorService() {
        val serviceIntent = Intent(application, PhoneCallMonitorService::class.java)
        ContextCompat.startForegroundService(application, serviceIntent)
    }
}