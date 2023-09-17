package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.ongoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.PhoneCallRepository

class GetOngoingPhoneCallUseCaseImplTest {

    // region Fields

    private val phoneCallRepositoryMock: PhoneCallRepository = mockk()

    private val getOngoingPhoneCallUseCase: GetOngoingPhoneCallUseCaseImpl =
        GetOngoingPhoneCallUseCaseImpl(
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
    fun `'execute()' should return an ongoing phone call when there is one`() = runTest {
        // given
        coEvery { phoneCallRepositoryMock.getOngoing() } returns ongoingPhoneCallDomainModel

        // when
        val actualResult = getOngoingPhoneCallUseCase.execute()

        // then
        assertEquals(ongoingPhoneCallDomainModel, actualResult)

        coVerify(ordering = Ordering.SEQUENCE) {
            phoneCallRepositoryMock.getOngoing()
        }
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
    }

    // endregion

}