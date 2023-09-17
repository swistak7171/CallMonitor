package pl.kamilszustak.callmonitor.data.phonecallmonitor.repository

import io.mockk.Ordering
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
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
    fun `'setStarted()' should change server status to started`() {
        // given
        justRun { serverStatusDataSourceMock.setStarted() }

        // when
        serverStatusRepository.setStarted()

        // then
        verify(ordering = Ordering.SEQUENCE) {
            serverStatusDataSourceMock.setStarted()
        }
    }

    @Test
    fun `'setStopped()' should change server status to stopped`() {
        // given
        justRun { serverStatusDataSourceMock.setStopped() }

        // when
        serverStatusRepository.setStopped()

        // then
        verify(ordering = Ordering.SEQUENCE) {
            serverStatusDataSourceMock.setStopped()
        }
    }

    @Test
    fun `'get()' should return a server status`() {
        mockkStatic(ServerStatusDataModel::toDomainModel) {
            // given
            every { serverStatusDataSourceMock.get() } returns serverStatusRunningDataModel
            every {
                serverStatusRunningDataModel.toDomainModel()
            } returns serverStatusRunningDomainModel

            // when
            val actualResult = serverStatusRepository.get()

            // then
            assertEquals(serverStatusRunningDomainModel, actualResult)

            verify(ordering = Ordering.SEQUENCE) {
                serverStatusDataSourceMock.get()
            }
        }
    }

    // endregion

}