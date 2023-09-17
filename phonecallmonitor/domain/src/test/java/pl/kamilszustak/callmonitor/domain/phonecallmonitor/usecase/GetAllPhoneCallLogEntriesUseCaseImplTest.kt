package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import app.cash.turbine.test
import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.phoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository
import kotlin.test.assertEquals

class GetAllPhoneCallLogEntriesUseCaseImplTest {

    // region Fields

    private val phoneCallRepositoryMock: PhoneCallRepository = mockk()

    private val getAllPhoneCallLogEntriesUseCase: GetAllPhoneCallLogEntriesUseCase =
        GetAllPhoneCallLogEntriesUseCaseImpl(
            phoneCallRepository = phoneCallRepositoryMock
        )

    // endregion

    // region Setup

    @After
    fun tearDown() {
        confirmVerified(phoneCallRepositoryMock)
    }

    // endregion

    // region Tests

    @Test
    fun `'execute()' should return a list of phone call log entries`() = runTest {
        // given
        val expectedResult = listOf(phoneCallLogEntryDomainModel)
        coEvery { phoneCallRepositoryMock.getAll() } returns expectedResult

        // when
        val actualResult = getAllPhoneCallLogEntriesUseCase.execute()

        // then
        assertEquals(expectedResult, actualResult)

        coVerify(ordering = Ordering.SEQUENCE) {
            phoneCallRepositoryMock.getAll()
        }
    }

    @Test
    fun `'executeRx()' should emit a list of phone call log entries`() = runTest {
        // given
        val expectedResults = listOf(
            emptyList(),
            listOf(phoneCallLogEntryDomainModel),
        )
        every { phoneCallRepositoryMock.getAllRx() } returns expectedResults.asFlow()

        // when
        getAllPhoneCallLogEntriesUseCase.executeRx()
            .test {
                // then
                expectedResults.forEach { expectedResult ->
                    assertEquals(expectedResult, awaitItem())
                }
                awaitComplete()
                ensureAllEventsConsumed()

                coVerify(ordering = Ordering.SEQUENCE) {
                    phoneCallRepositoryMock.getAllRx()
                }
            }
    }

    // endregion

}