package pl.kamilszustak.callmonitor.presentation.phonecallmonitor.viewmodel

import app.cash.turbine.test
import io.mockk.Ordering
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetAllPhoneCallLogEntriesUseCase
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase.GetServerConfigurationUseCase
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.mapper.toViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.model.PhoneCallLogViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.phoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.phoneCallLogEntryViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.phoneCallLogSuccessViewState
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.rule.MainDispatcherRule
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.serverConfigurationDomainModel
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PhoneCallLogViewModelTest {

    // region Fields

    private val getServerConfigurationUseCaseMock: GetServerConfigurationUseCase = mockk()
    private val getAllPhoneCallLogEntriesUseCaseMock: GetAllPhoneCallLogEntriesUseCase = mockk()

    private val standardTestDispatcher: TestDispatcher = StandardTestDispatcher()
    private val phoneCallLogViewModel: PhoneCallLogViewModel by lazy {
        PhoneCallLogViewModel(
            getServerConfigurationUseCase = getServerConfigurationUseCaseMock,
            getAllPhoneCallLogEntriesUseCase = getAllPhoneCallLogEntriesUseCaseMock
        )
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(
        testDispatcher = standardTestDispatcher
    )

    // endregion

    // region Setup

    @Before
    fun setUp() {
        every { getServerConfigurationUseCaseMock.execute() } returns serverConfigurationDomainModel
    }

    @After
    fun tearDown() {
        confirmVerified(
            getServerConfigurationUseCaseMock,
            getAllPhoneCallLogEntriesUseCaseMock,
        )
    }

    // endregion

    // region Tests

    @Test
    fun `'viewState' should emit loading view state at first`() = runTest(standardTestDispatcher) {
        // given
        val expectedResult = PhoneCallLogViewState.Loading
        every { getAllPhoneCallLogEntriesUseCaseMock.executeRx() } returns emptyFlow()

        // when
        phoneCallLogViewModel.viewState
            .test {
                runCurrent()

                // then
                assertEquals(expectedResult, awaitItem())
                ensureAllEventsConsumed()

                verify(ordering = Ordering.SEQUENCE) {
                    getServerConfigurationUseCaseMock.execute()
                    getAllPhoneCallLogEntriesUseCaseMock.executeRx()
                }
            }
    }

    @Test
    fun `'viewState' should emit success view state after data is loaded`() =
        runTest(standardTestDispatcher) {
            mockkStatic(PhoneCallLogEntryDomainModel::toViewState) {
                // given
                every {
                    getAllPhoneCallLogEntriesUseCaseMock.executeRx()
                } returns flowOf(listOf(phoneCallLogEntryDomainModel))
                every { phoneCallLogEntryDomainModel.toViewState() } returns phoneCallLogEntryViewState

                // when
                phoneCallLogViewModel.viewState
                    .test {
                        // then
                        assertEquals(PhoneCallLogViewState.Loading, awaitItem())
                        assertEquals(phoneCallLogSuccessViewState, awaitItem())
                        ensureAllEventsConsumed()

                        verify(ordering = Ordering.SEQUENCE) {
                            getServerConfigurationUseCaseMock.execute()
                            getAllPhoneCallLogEntriesUseCaseMock.executeRx()
                        }
                    }
            }

        }

    // endregion

}