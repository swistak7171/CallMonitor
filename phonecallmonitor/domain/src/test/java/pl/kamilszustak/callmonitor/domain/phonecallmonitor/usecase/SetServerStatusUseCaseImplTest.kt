package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import io.mockk.Ordering
import io.mockk.confirmVerified
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusEventDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository.ServerStatusRepository

class SetServerStatusUseCaseImplTest {

    // region Fields

    private val serverStatusRepositoryMock: ServerStatusRepository = mockk()

    private val setServerStatusUseCase: SetServerStatusUseCaseImpl = SetServerStatusUseCaseImpl(
        serverStatusRepository = serverStatusRepositoryMock
    )

    // endregion

    // region Tests

    @Test
    fun `'execute()' should change server status to started after receiving a started event`() {
        // given
        justRun { serverStatusRepositoryMock.setStarted() }

        // when
        setServerStatusUseCase.execute(ServerStatusEventDomainModel.Started)

        // then
        verify(ordering = Ordering.SEQUENCE) {
            serverStatusRepositoryMock.setStarted()
        }
        confirmVerified(serverStatusRepositoryMock)
    }

    @Test
    fun `'execute()' should change server status to stopped after receiving a stopped event`() {
        // given
        justRun { serverStatusRepositoryMock.setStopped() }

        // when
        setServerStatusUseCase.execute(ServerStatusEventDomainModel.Stopped)

        // then
        verify(ordering = Ordering.SEQUENCE) {
            serverStatusRepositoryMock.setStopped()
        }
        confirmVerified(serverStatusRepositoryMock)
    }

    // endregion

}