package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.datetime.Clock
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallEventDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallEventDataModel
import java.util.UUID

class PhoneCallEventDataSourceImpl(
    private val context: Context,
    private val clock: Clock,
) : PhoneCallEventDataSource {

    override fun getRx(): Flow<PhoneCallEventDataModel> {
        return callbackFlow {
            val receiver = createBroadcastReceiver { state ->
                trySend(state)
            }
            registerBroadcastReceiver(receiver)

            awaitClose {
                context.unregisterReceiver(receiver)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun createBroadcastReceiver(
        onEvent: (event: PhoneCallEventDataModel) -> Unit,
    ): BroadcastReceiver {
        return object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action != TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
                    return
                }

                val phoneNumber = intent.extras?.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                val state = intent.extras?.getString(TelephonyManager.EXTRA_STATE)
                if (phoneNumber.isNullOrBlank() || state.isNullOrBlank()) {
                    return
                }

                when (state) {
                    TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                        val event = PhoneCallEventDataModel.PhoneCallStart(
                            id = UUID.randomUUID(),
                            phoneNumber = phoneNumber,
                            timestamp = clock.now()
                        )
                        onEvent(event)
                    }

                    TelephonyManager.EXTRA_STATE_IDLE -> {
                        val event = PhoneCallEventDataModel.PhoneCallEnd(
                            phoneNumber = phoneNumber,
                            timestamp = clock.now()
                        )
                        onEvent(event)
                    }
                }
            }

        }
    }

    private fun registerBroadcastReceiver(receiver: BroadcastReceiver) {
        val filter = IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        val flags = ContextCompat.RECEIVER_EXPORTED
        ContextCompat.registerReceiver(context, receiver, filter, flags)
    }
}