package pl.kamilszustak.callmonitor.datasource

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pl.kamilszustak.callmonitor.model.PhoneCallState

class PhoneCallStateDataSourceImpl(
    private val context: Context,
) : PhoneCallStateDataSource {

    override fun getRx(): Flow<PhoneCallState> {
        return callbackFlow {
            val receiver = createBroadcastReceiver(
                onStarted = { phoneNumber ->
                    val state = PhoneCallState.Started(phoneNumber)
                    trySend(state)
                },
                onEnded = { phoneNumber ->
                    val state = PhoneCallState.Ended(phoneNumber)
                    trySend(state)
                }
            )
            registerBroadcastReceiver(receiver)

            awaitClose {
                context.unregisterReceiver(receiver)
            }
        }
    }

    private fun createBroadcastReceiver(
        onStarted: (phoneNumber: String) -> Unit,
        onEnded: (phoneNumber: String) -> Unit
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
                        onStarted(phoneNumber)
                    }

                    TelephonyManager.EXTRA_STATE_IDLE -> {
                        onEnded(phoneNumber)
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