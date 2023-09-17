package pl.kamilszustak.callmonitor.domain.phonecallmonitor.usecase

import io.mockk.Ordering
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
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

    // region Setup

    @After
    fun tearDown() {
        confirmVerified(serverStatusRepositoryMock)
    }

    // endregion

    // region Tests

    @Test
    fun `'execute()' should change server status to started after receiving a started event`() =
        runTest {
            // given
            coJustRun { serverStatusRepositoryMock.setStarted() }

            // when
            setServerStatusUseCase.execute(ServerStatusEventDomainModel.Started)

            // then
            coVerify(ordering = Ordering.SEQUENCE) {
                serverStatusRepositoryMock.setStarted()
            }
        }

    @Test
    fun `'execute()' should change server status to stopped after receiving a stopped event`() =
        runTest {
            // given
            coJustRun { serverStatusRepositoryMock.setStopped() }

            // when
            setServerStatusUseCase.execute(ServerStatusEventDomainModel.Stopped)

            // then
            coVerify(ordering = Ordering.SEQUENCE) {
                serverStatusRepositoryMock.setStopped()
            }
        }

    // endregion

}