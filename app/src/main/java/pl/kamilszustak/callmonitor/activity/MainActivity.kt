package pl.kamilszustak.callmonitor.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import pl.kamilszustak.callmonitor.service.PhoneCallMonitorService
import pl.kamilszustak.callmonitor.ui.MyApplicationTheme
import pl.kamilszustak.callmonitor.ui.phonecallmonitor.view.PhoneCallLogScreen

class MainActivity : ComponentActivity() {

    // region Fields

    private val requiredPermissions: Array<String> = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.READ_CONTACTS,
    )

    // endregion

    // region Activity Implementation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arePermissionsGranted = requiredPermissions
            .map { ContextCompat.checkSelfPermission(this, it) }
            .all { it == PackageManager.PERMISSION_GRANTED }

        val contract = ActivityResultContracts.RequestMultiplePermissions()
        val requestPermissionsLauncher = registerForActivityResult(contract) { permissions ->
            val allGranted = permissions.values.all { it }
            if (allGranted) {
                startPhoneCallMonitorService()
            } else {
                showPermissionsNotGrantedToast()
            }
        }

        if (!arePermissionsGranted) {
            requestPermissionsLauncher.launch(requiredPermissions)
        } else {
            startPhoneCallMonitorService()
        }

        setContent {
            MyApplicationTheme {
                PhoneCallLogScreen()
            }
        }
    }

    // endregion

    // region Private Methods

    private fun startPhoneCallMonitorService() {
        val serviceIntent = Intent(application, PhoneCallMonitorService::class.java)
        ContextCompat.startForegroundService(application, serviceIntent)
    }

    private fun showPermissionsNotGrantedToast() {
        Toast.makeText(
            this,
            "Required permissions not granted, phone call monitoring cannot be started",
            Toast.LENGTH_LONG
        )
            .show()
    }

    // endregion

}