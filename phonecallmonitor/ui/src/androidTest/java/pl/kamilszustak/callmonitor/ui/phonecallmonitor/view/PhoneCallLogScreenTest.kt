package pl.kamilszustak.callmonitor.ui.phonecallmonitor.view

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.viewmodel.PhoneCallLogViewModel
import pl.kamilszustak.callmonitor.ui.phonecallmonitor.R
import pl.kamilszustak.callmonitor.ui.phonecallmonitor.phoneCallLogSuccessViewState
import pl.kamilszustak.callmonitor.ui.phonecallmonitor.phoneCallLogSuccessViewStateWithEmptyLog
import pl.kamilszustak.callmonitor.ui.phonecallmonitor.serverHost
import pl.kamilszustak.callmonitor.ui.phonecallmonitor.serverPort

// region Constants

private const val LOADING_INDICATOR_TAG: String = "loading_indicator"
private const val SERVER_INFORMATION_CARD_TAG: String = "server_information_card"
private const val SERVER_HOST_TEXT_TAG: String = "server_host_text"
private const val SERVER_PORT_TEXT_TAG: String = "server_port_text"
private const val PHONE_CALL_LOG_CARD_TAG: String = "phone_call_log_card"
private const val EMPTY_PHONE_CALL_LOG_TAG: String = "empty_phone_call_log_text"
private const val PHONE_CALL_LOG_TAG: String = "phone_call_log"
private const val PHONE_CALL_LOG_ENTRY_PRIMARY_TEXT_TAG: String =
    "phone_call_log_entry_primary_text_%s"
private const val PHONE_CALL_LOG_ENTRY_SECONDARY_TEXT_TAG: String =
    "phone_call_log_entry_secondary_text_%s"
private const val PHONE_CALL_LOG_ENTRY_DURATION_TEXT_TAG: String =
    "phone_call_log_entry_duration_text_%s"

// endregion

class PhoneCallLogScreenTest {

    // region Fields

    private val phoneCallLogViewModelMock: PhoneCallLogViewModel = mockk()

    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    // endregion

    // region Tests

    @Test
    fun testLoadingState_loadingIndicatorVisible() {
        // given
        every {
            phoneCallLogViewModelMock.viewState
        } returns MutableStateFlow(PhoneCallLogViewState.Loading)

        // when
        composeTestRule.setContent {
            PhoneCallLogScreen(phoneCallLogViewModelMock)
        }

        // then
        composeTestRule.onNodeWithTag(LOADING_INDICATOR_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun testLoadingState_serverInformationNotVisible() {
        // given
        every {
            phoneCallLogViewModelMock.viewState
        } returns MutableStateFlow(PhoneCallLogViewState.Loading)

        // when
        composeTestRule.setContent {
            PhoneCallLogScreen(phoneCallLogViewModelMock)
        }

        // then
        composeTestRule.onNodeWithTag(SERVER_INFORMATION_CARD_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun testLoadingState_phoneCallLogNotVisible() {
        // given
        every {
            phoneCallLogViewModelMock.viewState
        } returns MutableStateFlow(PhoneCallLogViewState.Loading)

        // when
        composeTestRule.setContent {
            PhoneCallLogScreen(phoneCallLogViewModelMock)
        }

        // then
        composeTestRule.onNodeWithTag(PHONE_CALL_LOG_CARD_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun testSuccessState_loadingIndicatorNotVisible() {
        // given
        every {
            phoneCallLogViewModelMock.viewState
        } returns MutableStateFlow(phoneCallLogSuccessViewStateWithEmptyLog)

        // when
        composeTestRule.setContent {
            PhoneCallLogScreen(phoneCallLogViewModelMock)
        }

        // then
        composeTestRule.onNodeWithTag(LOADING_INDICATOR_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun testSuccessState_serverInformationVisible() {
        // given
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedServerHostText =
            context.getString(R.string.phone_call_log_screen_server_host, serverHost)
        val expectedServerPortText =
            context.getString(R.string.phone_call_log_screen_server_port, serverPort)

        every {
            phoneCallLogViewModelMock.viewState
        } returns MutableStateFlow(phoneCallLogSuccessViewStateWithEmptyLog)

        // when
        composeTestRule.setContent {
            PhoneCallLogScreen(phoneCallLogViewModelMock)
        }

        // then
        composeTestRule.onNodeWithTag(SERVER_INFORMATION_CARD_TAG)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(SERVER_HOST_TEXT_TAG)
            .assertIsDisplayed()
            .assertTextEquals(expectedServerHostText)
        composeTestRule.onNodeWithTag(SERVER_PORT_TEXT_TAG)
            .assertIsDisplayed()
            .assertTextEquals(expectedServerPortText)
    }

    @Test
    fun testSuccessState_emptyPhoneCallLog() {
        // given
        every {
            phoneCallLogViewModelMock.viewState
        } returns MutableStateFlow(phoneCallLogSuccessViewStateWithEmptyLog)

        // when
        composeTestRule.setContent {
            PhoneCallLogScreen(phoneCallLogViewModelMock)
        }

        // then
        composeTestRule.onNodeWithTag(PHONE_CALL_LOG_CARD_TAG)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(EMPTY_PHONE_CALL_LOG_TAG)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(PHONE_CALL_LOG_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun testSuccessState_filledPhoneCallLog() {
        // given
        every {
            phoneCallLogViewModelMock.viewState
        } returns MutableStateFlow(phoneCallLogSuccessViewState)

        // when
        composeTestRule.setContent {
            PhoneCallLogScreen(phoneCallLogViewModelMock)
        }

        // then
        composeTestRule.onNodeWithTag(PHONE_CALL_LOG_CARD_TAG)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(EMPTY_PHONE_CALL_LOG_TAG)
            .assertDoesNotExist()
        composeTestRule.onNodeWithTag(PHONE_CALL_LOG_TAG)
            .assertIsDisplayed()

        phoneCallLogSuccessViewState.entries
            .forEach { entry ->
                val expectedPrimaryText = entry.contactName ?: entry.phoneNumber
                composeTestRule.onNodeWithTag(PHONE_CALL_LOG_ENTRY_PRIMARY_TEXT_TAG.format(entry.id))
                    .assertIsDisplayed()
                    .assertTextEquals(expectedPrimaryText)

                val secondaryTextInteraction = composeTestRule.onNodeWithTag(
                    PHONE_CALL_LOG_ENTRY_SECONDARY_TEXT_TAG.format(entry.id)
                )
                if (entry.contactName != null) {
                    secondaryTextInteraction.assertIsDisplayed()
                        .assertTextEquals(entry.phoneNumber)
                } else {
                    secondaryTextInteraction.assertDoesNotExist()
                }

                composeTestRule.onNodeWithTag(PHONE_CALL_LOG_ENTRY_DURATION_TEXT_TAG.format(entry.id))
                    .assertIsDisplayed()
                    .assertTextEquals(entry.duration.toString())
            }
    }

    // endregion

}