package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import io.mockk.Ordering
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.phoneCallEndEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.phoneCallStartEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallEventRepository
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository

class MonitorPhoneCallsUseCaseImplTest {

    // region Fields

    private val phoneCallEventRepositoryMock: PhoneCallEventRepository = mockk()
    private val phoneCallRepositoryMock: PhoneCallRepository = mockk()

    private val monitorPhoneCallsUseCase: MonitorPhoneCallsUseCase = MonitorPhoneCallsUseCaseImpl(
        phoneCallEventRepository = phoneCallEventRepositoryMock,
        phoneCallRepository = phoneCallRepositoryMock
    )

    // endregion

    // region Setup

    @After
    fun tearDown() {
        confirmVerified(phoneCallEventRepositoryMock, phoneCallRepositoryMock)
    }

    // endregion

    // region Tests

    @Test
    fun `'execute()' should change phone call status to started after receiving a phone call start event`() =
        runTest {
            // given
            every {
                phoneCallEventRepositoryMock.getRx()
            } returns flowOf(phoneCallStartEventDomainModel)
            coJustRun { phoneCallRepositoryMock.setStarted(phoneCallStartEventDomainModel) }

            // when
            monitorPhoneCallsUseCase.execute()

            // then
            coVerify(ordering = Ordering.SEQUENCE) {
                phoneCallEventRepositoryMock.getRx()
                phoneCallRepositoryMock.setStarted(phoneCallStartEventDomainModel)
            }
        }

    @Test
    fun `'execute()' should change phone call status to ended after receiving a phone call end event`() =
        runTest {
            // given
            every {
                phoneCallEventRepositoryMock.getRx()
            } returns flowOf(phoneCallEndEventDomainModel)
            coJustRun { phoneCallRepositoryMock.setEnded(phoneCallEndEventDomainModel) }

            // when
            monitorPhoneCallsUseCase.execute()

            // then
            coVerify(ordering = Ordering.SEQUENCE) {
                phoneCallEventRepositoryMock.getRx()
                phoneCallRepositoryMock.setEnded(phoneCallEndEventDomainModel)
            }
        }

    @Test
    fun `'execute()' should react on phone call events and change phone call status accordingly`() =
        runTest {
            // given
            val events = listOf(phoneCallStartEventDomainModel, phoneCallEndEventDomainModel)

            every { phoneCallEventRepositoryMock.getRx() } returns events.asFlow()
            coJustRun { phoneCallRepositoryMock.setStarted(phoneCallStartEventDomainModel) }
            coJustRun { phoneCallRepositoryMock.setEnded(phoneCallEndEventDomainModel) }

            // when
            monitorPhoneCallsUseCase.execute()

            // then
            coVerify(ordering = Ordering.SEQUENCE) {
                phoneCallEventRepositoryMock.getRx()
                phoneCallRepositoryMock.setStarted(phoneCallStartEventDomainModel)
                phoneCallRepositoryMock.setEnded(phoneCallEndEventDomainModel)
            }
        }

    // endregion

}