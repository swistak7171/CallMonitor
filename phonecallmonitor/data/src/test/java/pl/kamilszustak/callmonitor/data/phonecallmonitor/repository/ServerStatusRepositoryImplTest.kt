package pl.kamilszustak.callmonitor.data.phonecallmonitor.repository

import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerStatusDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.serverStatusRunningDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.serverStatusRunningDomainModel
import kotlin.test.assertEquals

class ServerStatusRepositoryImplTest {

    // region Fields

    private val serverStatusDataSourceMock: ServerStatusDataSource = mockk()

    private val serverStatusRepository: ServerStatusRepositoryImpl = ServerStatusRepositoryImpl(
        serverStatusDataSource = serverStatusDataSourceMock
    )

    // endregion

    // region Setup

    @After
    fun tearDown() {
        confirmVerified(serverStatusDataSourceMock)
    }

    // endregion

    // region Tests

    @Test
    fun `'setStarted()' should change server status to started`() = runTest {
        // given
        coJustRun { serverStatusDataSourceMock.setStarted() }

        // when
        serverStatusRepository.setStarted()

        // then
        coVerify(ordering = Ordering.SEQUENCE) {
            serverStatusDataSourceMock.setStarted()
        }
    }

    @Test
    fun `'setStopped()' should change server status to stopped`() = runTest {
        // given
        coJustRun { serverStatusDataSourceMock.setStopped() }

        // when
        serverStatusRepository.setStopped()

        // then
        coVerify(ordering = Ordering.SEQUENCE) {
            serverStatusDataSourceMock.setStopped()
        }
    }

    @Test
    fun `'get()' should return a server status`() = runTest {
        mockkStatic(ServerStatusDataModel::toDomainModel) {
            // given
            coEvery { serverStatusDataSourceMock.get() } returns serverStatusRunningDataModel
            every {
                serverStatusRunningDataModel.toDomainModel()
            } returns serverStatusRunningDomainModel

            // when
            val actualResult = serverStatusRepository.get()

            // then
            assertEquals(serverStatusRunningDomainModel, actualResult)

            coVerify(ordering = Ordering.SEQUENCE) {
                serverStatusDataSourceMock.get()
            }
        }
    }

    // endregion

}