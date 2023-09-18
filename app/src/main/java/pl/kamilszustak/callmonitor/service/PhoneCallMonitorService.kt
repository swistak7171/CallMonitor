package pl.kamilszustak.callmonitor.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject
import pl.kamilszustak.callmonitor.R
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.MonitorPhoneCallsUseCase

// region Constants

private const val SERVICE_ID: Int = 7171
private const val NOTIFICATION_CHANNEL_ID = "phone-call-monitor"

// endregion

class PhoneCallMonitorService : Service() {

    // region Fields

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    private val monitorPhoneCallsUseCase: MonitorPhoneCallsUseCase by inject()

    // endregion

    // region Service Implementation

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val notification = createNotification()
        startForeground(SERVICE_ID, notification)

        coroutineScope.launch {
            monitorPhoneCallsUseCase.execute()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        coroutineScope.cancel()

        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    // endregion

    // region Private Methods

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(getString(R.string.phone_call_monitor_service_notification_title))
            .build()
    }

    private fun createNotificationChannel() {
        val notificationManager = NotificationManagerCompat.from(this)
        val notificationChannel = NotificationChannelCompat.Builder(
            NOTIFICATION_CHANNEL_ID,
            NotificationManagerCompat.IMPORTANCE_HIGH
        )
            .setName(getString(R.string.phone_call_monitor_service_notification_channel_name))
            .setDescription(getString(R.string.phone_call_monitor_service_notification_channel_description))
            .build()
        notificationManager.createNotificationChannel(notificationChannel)
    }

    // endregion

}