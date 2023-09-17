package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerStatusRepository
import kotlin.test.assertEquals

class GetServerStatusUseCaseImplTest {

    // region Fields

    private val serverStatusRepositoryMock: ServerStatusRepository = mockk()

    private val getServerStatusUseCase: GetServerStatusUseCaseImpl = GetServerStatusUseCaseImpl(
        serverStatusRepository = serverStatusRepositoryMock
    )

    // endregion

    // region Tests

    @Test
    fun `'execute()' should return a current server status`() = runTest {
        // given
        val expectedResult = ServerStatusDomainModel.Stopped
        coEvery { serverStatusRepositoryMock.get() } returns expectedResult

        // when
        val actualResult = getServerStatusUseCase.execute()

        // then
        assertEquals(expectedResult, actualResult)

        coVerify(ordering = Ordering.SEQUENCE) {
            serverStatusRepositoryMock.get()
        }
        confirmVerified(serverStatusRepositoryMock)
    }

    // endregion

}