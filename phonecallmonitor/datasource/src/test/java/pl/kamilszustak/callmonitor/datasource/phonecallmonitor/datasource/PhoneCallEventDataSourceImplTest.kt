package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import io.mockk.CapturingSlot
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.slot
import io.mockk.unmockkStatic
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.id
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.phoneCallEndEventDataModel
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.phoneCallEndTimestamp
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.phoneCallStartEventDataModel
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.phoneCallStartTimestamp
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.phoneNumber
import pl.kamilszustak.callmonitor.logger.Logger
import java.util.UUID
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class PhoneCallEventDataSourceImplTest {

    // region Fields

    private val broadcastReceiverSlot: CapturingSlot<BroadcastReceiver> = slot()
    private val intentFilterSlot: CapturingSlot<IntentFilter> = slot()
    private val intentMock: Intent = mockk()
    private val extrasMock: Bundle = mockk()
    private val loggerMock: Logger = mockk()
    private val contextMock: Context = mockk()
    private val clockMock: Clock = mockk()

    private val phoneCallEventDataSource: PhoneCallEventDataSourceImpl =
        PhoneCallEventDataSourceImpl(
            logger = loggerMock,
            context = contextMock,
            clock = clockMock
        )

    // endregion

    // region Setup

    @Before
    fun setUp() {
        mockkStatic(ContextCompat::class)

        every {
            ContextCompat.registerReceiver(
                /* context = */ contextMock,
                /* receiver = */ capture(broadcastReceiverSlot),
                /* filter = */ capture(intentFilterSlot),
                /* flags = */ ContextCompat.RECEIVER_EXPORTED
            )
        } returns mockk()
        every { intentMock.extras } returns extrasMock
        justRun { contextMock.unregisterReceiver(any()) }
        justRun { loggerMock.warn(any(), any()) }
    }

    @After
    fun tearDown() {
        val intentFilterAction = intentFilterSlot.captured.getAction(0)
        assertEquals(TelephonyManager.ACTION_PHONE_STATE_CHANGED, intentFilterAction)

        verify(exactly = 1) {
            ContextCompat.registerReceiver(
                /* context = */ contextMock,
                /* receiver = */ broadcastReceiverSlot.captured,
                /* filter = */ intentFilterSlot.captured,
                /* flags = */ ContextCompat.RECEIVER_EXPORTED
            )
            contextMock.unregisterReceiver(broadcastReceiverSlot.captured)
        }
        confirmVerified(intentMock, extrasMock, loggerMock, contextMock, clockMock)

        unmockkStatic(ContextCompat::class)
    }

    // endregion

    // region Tests

    @Test
    fun `'getRx()' should not emit anything when BroadcastReceiver does not receive any Intent broadcast`() =
        runTest {
            // when
            phoneCallEventDataSource.getRx()
                .test {
                    // then
                    ensureAllEventsConsumed()
                    cancel()
                }
        }

    @Test
    fun `'getRx()' should not emit anything when BroadcastReceiver does not receive Intent broadcast with proper action`() =
        runTest {
            // given
            every { intentMock.action } returns "invalid_action"

            // when
            phoneCallEventDataSource.getRx()
                .test {
                    broadcastReceiverSlot.captured.onReceive(contextMock, intentMock)

                    // then
                    ensureAllEventsConsumed()
                    cancel()

                    verify(exactly = 1) {
                        intentMock.action
                        loggerMock.warn(any(), any())
                    }
                }
        }

    @Test
    fun `'getRx()' should not emit anything when BroadcastReceiver receives Intent broadcast without extras state`() =
        runTest {
            // given
            every { intentMock.action } returns TelephonyManager.ACTION_PHONE_STATE_CHANGED
            every { extrasMock.getString(TelephonyManager.EXTRA_STATE) } returns null

            // when
            phoneCallEventDataSource.getRx()
                .test {
                    broadcastReceiverSlot.captured.onReceive(contextMock, intentMock)

                    // then
                    ensureAllEventsConsumed()
                    cancel()

                    verify(exactly = 1) {
                        intentMock.action
                        intentMock.extras
                        extrasMock.getString(TelephonyManager.EXTRA_STATE)
                        loggerMock.warn(any(), any())
                    }
                }
        }

    @Test
    fun `'getRx()' should not emit anything when BroadcastReceiver receives Intent broadcast without extras phone number`() =
        runTest {
            // given
            every { intentMock.action } returns TelephonyManager.ACTION_PHONE_STATE_CHANGED
            every {
                extrasMock.getString(TelephonyManager.EXTRA_STATE)
            } returns TelephonyManager.EXTRA_STATE_OFFHOOK
            every {
                extrasMock.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
            } returns null

            // when
            phoneCallEventDataSource.getRx()
                .test {
                    broadcastReceiverSlot.captured.onReceive(contextMock, intentMock)

                    // then
                    ensureAllEventsConsumed()
                    cancel()

                    verify(exactly = 1) {
                        intentMock.action
                        extrasMock.getString(TelephonyManager.EXTRA_STATE)
                        extrasMock.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                        loggerMock.warn(any(), any())
                    }
                    verify(exactly = 2) {
                        intentMock.extras
                    }
                }
        }

    @Test
    fun `'getRx()' should not emit anything when BroadcastReceiver receives Intent broadcast with extras state other than off hook and idle`() =
        runTest {
            // given
            every { intentMock.action } returns TelephonyManager.ACTION_PHONE_STATE_CHANGED
            every {
                extrasMock.getString(TelephonyManager.EXTRA_STATE)
            } returns TelephonyManager.EXTRA_STATE_RINGING
            every {
                extrasMock.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
            } returns phoneNumber

            // when
            phoneCallEventDataSource.getRx()
                .test {
                    broadcastReceiverSlot.captured.onReceive(contextMock, intentMock)

                    // then
                    ensureAllEventsConsumed()
                    cancel()

                    verify(exactly = 1) {
                        intentMock.action
                        extrasMock.getString(TelephonyManager.EXTRA_STATE)
                        extrasMock.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                    }
                    verify(exactly = 2) {
                        intentMock.extras
                    }
                }
        }

    @Test
    fun `'getRx()' should emit phone call start event when BroadcastReceiver receives Intent broadcast with extras state off hook`() =
        runTest {
            mockkStatic(UUID::class) {
                // given
                every { intentMock.action } returns TelephonyManager.ACTION_PHONE_STATE_CHANGED
                every {
                    extrasMock.getString(TelephonyManager.EXTRA_STATE)
                } returns TelephonyManager.EXTRA_STATE_OFFHOOK
                every {
                    extrasMock.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                } returns phoneNumber
                every { UUID.randomUUID().toString() } returns id
                every { clockMock.now() } returns phoneCallStartTimestamp

                // when
                phoneCallEventDataSource.getRx()
                    .test {
                        broadcastReceiverSlot.captured.onReceive(contextMock, intentMock)

                        // then
                        assertEquals(phoneCallStartEventDataModel, awaitItem())
                        ensureAllEventsConsumed()
                        cancel()

                        verify(exactly = 1) {
                            intentMock.action
                            extrasMock.getString(TelephonyManager.EXTRA_STATE)
                            extrasMock.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                            UUID.randomUUID().toString()
                            clockMock.now()
                        }
                        verify(exactly = 2) {
                            intentMock.extras
                        }
                    }
            }
        }

    @Test
    fun `'getRx()' should emit phone call end event when BroadcastReceiver receives Intent broadcast with extras state idle`() =
        runTest {
            mockkStatic(UUID::class) {
                // given
                every { intentMock.action } returns TelephonyManager.ACTION_PHONE_STATE_CHANGED
                every {
                    extrasMock.getString(TelephonyManager.EXTRA_STATE)
                } returns TelephonyManager.EXTRA_STATE_IDLE
                every {
                    extrasMock.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                } returns phoneNumber
                every { clockMock.now() } returns phoneCallEndTimestamp

                // when
                phoneCallEventDataSource.getRx()
                    .test {
                        broadcastReceiverSlot.captured.onReceive(contextMock, intentMock)

                        // then
                        assertEquals(phoneCallEndEventDataModel, awaitItem())
                        ensureAllEventsConsumed()
                        cancel()

                        verify(exactly = 1) {
                            intentMock.action
                            extrasMock.getString(TelephonyManager.EXTRA_STATE)
                            extrasMock.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
                            clockMock.now()
                        }
                        verify(exactly = 2) {
                            intentMock.extras
                        }
                    }
            }
        }

    // endregion

}