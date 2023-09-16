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
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallEventDomainModel
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

    // region Tests

    @Test
    fun `'execute()' should change phone call status to started after receiving a phone call start event`() =
        runTest {
            // given
            val event = mockk<PhoneCallEventDomainModel.PhoneCallStart>()

            every { phoneCallEventRepositoryMock.getRx() } returns flowOf(event)
            coJustRun { phoneCallRepositoryMock.setStarted(event) }

            // when
            monitorPhoneCallsUseCase.execute()

            // then
            coVerify(ordering = Ordering.SEQUENCE) {
                phoneCallEventRepositoryMock.getRx()
                phoneCallRepositoryMock.setStarted(event)
            }
            confirmVerified(phoneCallEventRepositoryMock, phoneCallRepositoryMock)
        }

    @Test
    fun `'execute()' should change phone call status to ended after receiving a phone call end event`() =
        runTest {
            // given
            val event = mockk<PhoneCallEventDomainModel.PhoneCallEnd>()

            every { phoneCallEventRepositoryMock.getRx() } returns flowOf(event)
            coJustRun { phoneCallRepositoryMock.setEnded(event) }

            // when
            monitorPhoneCallsUseCase.execute()

            // then
            coVerify(ordering = Ordering.SEQUENCE) {
                phoneCallEventRepositoryMock.getRx()
                phoneCallRepositoryMock.setEnded(event)
            }
            confirmVerified(phoneCallEventRepositoryMock, phoneCallRepositoryMock)
        }

    @Test
    fun `'execute()' should react on phone call events and change phone call status accordingly`() =
        runTest {
            // given
            val startEvent = mockk<PhoneCallEventDomainModel.PhoneCallStart>()
            val endEvent = mockk<PhoneCallEventDomainModel.PhoneCallEnd>()
            val events = listOf(startEvent, endEvent)

            every { phoneCallEventRepositoryMock.getRx() } returns events.asFlow()
            coJustRun { phoneCallRepositoryMock.setStarted(startEvent) }
            coJustRun { phoneCallRepositoryMock.setEnded(endEvent) }

            // when
            monitorPhoneCallsUseCase.execute()

            // then
            coVerify(ordering = Ordering.SEQUENCE) {
                phoneCallEventRepositoryMock.getRx()
                phoneCallRepositoryMock.setStarted(startEvent)
                phoneCallRepositoryMock.setEnded(endEvent)
            }
            confirmVerified(phoneCallEventRepositoryMock, phoneCallRepositoryMock)
        }

    // endregion

}