package pl.kamilszustak.callmonitor.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import pl.kamilszustak.callmonitor.service.PhoneCallMonitorService
import pl.kamilszustak.callmonitor.ui.ApplicationTheme
import pl.kamilszustak.callmonitor.ui.phonecallmonitor.view.PhoneCallLogScreen

/**
 * An [Activity] responsible for requesting required permissions and starting/stopping the
 * [PhoneCallMonitorService]. If the required permissions are not granted, a [Toast] is shown. If
 * the permissions are granted, the [PhoneCallMonitorService] is started in foreground. This
 * [Activity] also hosts the [PhoneCallLogScreen] composable.
 */
class MainActivity : ComponentActivity() {

    // region Fields

    /**
     * An [Array] of required permissions needed for [PhoneCallMonitorService] to work properly.
     */
    private val requiredPermissions: Array<String> = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.READ_CONTACTS,
    )

    /**
     * An [Intent] used to start and stop the [PhoneCallMonitorService].
     */
    private val serviceIntent: Intent
        get() = Intent(application, PhoneCallMonitorService::class.java)

    // endregion

    // region Activity Implementation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        managePermissions()

        setContent {
            ApplicationTheme {
                PhoneCallLogScreen()
            }
        }
    }

    override fun onDestroy() {
        stopService(serviceIntent)

        super.onDestroy()
    }

    // endregion

    // region Private Methods

    /**
     * Requests required permissions and starts the [PhoneCallMonitorService] if all of them are
     * granted. Otherwise, a [Toast] is shown.
     */
    private fun managePermissions() {
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
    }

    /**
     * Starts the [PhoneCallMonitorService] in foreground.
     */
    private fun startPhoneCallMonitorService() {
        val serviceIntent = Intent(application, PhoneCallMonitorService::class.java)
        ContextCompat.startForegroundService(application, serviceIntent)
    }

    /**
     * Shows a [Toast] informing that the required permissions are not granted.
     */
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