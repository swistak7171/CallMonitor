package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.PhoneCallLogEntryDomainModel
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

    // region Tests

    @Test
    fun `'execute()' should return a list of phone call log entries`() = runTest {
        // given
        val expectedResult = listOf<PhoneCallLogEntryDomainModel>(mockk(), mockk())
        coEvery { phoneCallRepositoryMock.getAll() } returns expectedResult

        // when
        val actualResult = getAllPhoneCallLogEntriesUseCase.execute()

        // then
        assertEquals(expectedResult, actualResult)

        coVerify(exactly = 1) {
            phoneCallRepositoryMock.getAll()
        }
        confirmVerified(phoneCallRepositoryMock)
    }

    @Test
    fun `'executeRx()' should emit a list of phone call log entries`() = runTest {
        // given
        val expectedResults = listOf<List<PhoneCallLogEntryDomainModel>>(
            emptyList(),
            listOf(mockk()),
            listOf(mockk(), mockk()),
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

                coVerify(exactly = 1) {
                    phoneCallRepositoryMock.getAllRx()
                }
                confirmVerified(phoneCallRepositoryMock)
            }
    }

    // endregion

}