package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.OngoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository
import java.util.UUID

class GetOngoingPhoneCallUseCaseImplTest {

    // region Fields

    private val phoneCallRepositoryMock: PhoneCallRepository = mockk()

    private val getOngoingPhoneCallUseCase: GetOngoingPhoneCallUseCaseImpl =
        GetOngoingPhoneCallUseCaseImpl(
            phoneCallRepository = phoneCallRepositoryMock
        )

    // endregion

    // region Tests

    @Test
    fun `'execute()' should return an ongoing phone call when there is one`() = runTest {
        // given
        val expectedResult = OngoingPhoneCallDomainModel(
            id = UUID.randomUUID(),
            phoneNumber = "123456789",
            contactName = "John Smith"
        )
        coEvery { phoneCallRepositoryMock.getOngoing() } returns expectedResult

        // when
        val actualResult = getOngoingPhoneCallUseCase.execute()

        // then
        assertEquals(expectedResult, actualResult)

        coVerify(ordering = Ordering.SEQUENCE) {
            phoneCallRepositoryMock.getOngoing()
        }
        confirmVerified(phoneCallRepositoryMock)
    }

    @Test
    fun `'execute()' should return 'null' when there is no ongoing phone call`() = runTest {
        // given
        coEvery { phoneCallRepositoryMock.getOngoing() } returns null

        // when
        val actualResult = getOngoingPhoneCallUseCase.execute()

        // then
        assertNull(actualResult)

        coVerify(ordering = Ordering.SEQUENCE) {
            phoneCallRepositoryMock.getOngoing()
        }
        confirmVerified(phoneCallRepositoryMock)
    }

    // endregion

}