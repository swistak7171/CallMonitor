package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
    fun `'execute()' should return a current server status`() {
        // given
        val expectedResult = ServerStatusDomainModel.Stopped
        every { serverStatusRepositoryMock.get() } returns expectedResult

        // when
        val actualResult = getServerStatusUseCase.execute()

        // then
        assertEquals(expectedResult, actualResult)

        verify(exactly = 1) {
            serverStatusRepositoryMock.get()
        }
        confirmVerified(serverStatusRepositoryMock)
    }

    // endregion

}