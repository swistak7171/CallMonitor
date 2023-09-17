package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import io.mockk.Ordering
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerConfigurationRepository
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.serverConfigurationDomainModel
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
        every { serverConfigurationRepositoryMock.get() } returns serverConfigurationDomainModel

        // when
        val actualResult = getServerConfigurationUseCase.execute()

        // then
        assertEquals(serverConfigurationDomainModel, actualResult)

        verify(ordering = Ordering.SEQUENCE) {
            serverConfigurationRepositoryMock.get()
        }
        confirmVerified(serverConfigurationRepositoryMock)
    }

    // endregion

}