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
import pl.kamilszustak.callmonitor.logger.Logger
import java.util.UUID

// region Constants

private const val TAG: String = "PhoneCallEventDataSourceImpl"

// endregion

internal class PhoneCallEventDataSourceImpl(
    private val logger: Logger,
    private val context: Context,
    private val clock: Clock,
) : PhoneCallEventDataSource {

    // region PhoneCallEventDataSource implementation

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

    // endregion

    // region Private Methods

    @Suppress("DEPRECATION")
    private fun createBroadcastReceiver(
        onEvent: (event: PhoneCallEventDataModel) -> Unit,
    ): BroadcastReceiver {
        return object : BroadcastReceiver() {

            // region BroadcastReceiver Implementation

            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action != TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
                    logger.warn(TAG) { "Received an unexpected intent action: ${intent?.action}" }
                    return
                }

                val state = intent.extras?.getString(TelephonyManager.EXTRA_STATE)
                if (state.isNullOrBlank()) {
                    logger.warn(TAG) { "Received an intent with null or blank extras state" }
                    return
                }

                val phoneNumber = intent.extras?.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                if (phoneNumber.isNullOrBlank()) {
                    logger.warn(TAG) { "Received an intent with null or blank extras phone number" }
                    return
                }

                when (state) {
                    TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                        val event = PhoneCallEventDataModel.PhoneCallStart(
                            id = UUID.randomUUID()
                                .toString(),
                            timestamp = clock.now(),
                            phoneNumber = phoneNumber
                        )
                        onEvent(event)
                    }

                    TelephonyManager.EXTRA_STATE_IDLE -> {
                        val event = PhoneCallEventDataModel.PhoneCallEnd(
                            timestamp = clock.now(),
                            phoneNumber = phoneNumber
                        )
                        onEvent(event)
                    }
                }
            }

            // endregion

        }
    }

    private fun registerBroadcastReceiver(receiver: BroadcastReceiver) {
        val filter = IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        val flags = ContextCompat.RECEIVER_EXPORTED
        ContextCompat.registerReceiver(
            /* context = */ context,
            /* receiver = */ receiver,
            /* filter = */ filter,
            /* flags = */ flags
        )
    }

    // endregion

}