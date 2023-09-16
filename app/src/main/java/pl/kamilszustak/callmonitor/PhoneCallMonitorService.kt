package pl.kamilszustak.callmonitor

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
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.MonitorPhoneCallsUseCase

private const val SERVICE_ID: Int = 7171
private const val NOTIFICATION_CHANNEL_ID = "phone-call-monitor"

class PhoneCallMonitorService : Service() {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    private val monitorPhoneCallsUseCase: MonitorPhoneCallsUseCase by inject()

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

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Phone call monitor")
            .build()
    }

    private fun createNotificationChannel() {
        val notificationManager = NotificationManagerCompat.from(this)
        val notificationChannel = NotificationChannelCompat.Builder(
            NOTIFICATION_CHANNEL_ID,
            NotificationManagerCompat.IMPORTANCE_HIGH
        )
            .setName("Phone call monitor")
            .build()
        notificationManager.createNotificationChannel(notificationChannel)
    }

}