package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import io.mockk.Ordering
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerConfigurationDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerConfigurationRepository
import kotlin.test.assertEquals

class GetServerConfigurationUseCaseImplTest {

    // region Fields

    private val serverConfigurationRepositoryMock: ServerConfigurationRepository = mockk()

    private val getServerConfigurationUseCase: GetServerConfigurationUseCaseImpl =
        GetServerConfigurationUseCaseImpl(
            serverConfigurationRepository = serverConfigurationRepositoryMock
        )

    // endregion

    // region Tests

    @Test
    fun `'execute()' should return a server configuration`() {
        // given
        val expectedResult = ServerConfigurationDomainModel(
            host = "123.123.123.123",
            port = 9090
        )
        every { serverConfigurationRepositoryMock.get() } returns expectedResult

        // when
        val actualResult = getServerConfigurationUseCase.execute()

        // then
        assertEquals(expectedResult, actualResult)

        verify(ordering = Ordering.SEQUENCE) {
            serverConfigurationRepositoryMock.get()
        }
        confirmVerified(serverConfigurationRepositoryMock)
    }

    // endregion

}