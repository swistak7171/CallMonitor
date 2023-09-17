package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import io.mockk.Ordering
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.datetime.Clock
import org.junit.After
import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.serverStartTimestamp
import kotlin.test.assertEquals

class ServerStatusDataSourceImplTest {

    // region Fields

    private val clockMock: Clock = mockk()

    private val serverStatusDataSource: ServerStatusDataSourceImpl = ServerStatusDataSourceImpl(
        clock = clockMock
    )

    // endregion

    // region Setup

    @After
    fun tearDown() {
        confirmVerified(clockMock)
    }

    // endregion

    // region Tests

    @Test
    fun `'get()' should return server status stopped when it has not been started`() {
        // given
        val expectedResult = ServerStatusDataModel.Stopped

        // when
        val actualResult = serverStatusDataSource.get()

        // then
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `'get()' should return server status started when it has been started`() {
        // given
        val expectedResult = ServerStatusDataModel.Running(
            startTimestamp = serverStartTimestamp
        )

        every { clockMock.now() } returns serverStartTimestamp
        serverStatusDataSource.setStarted()

        // when
        val actualResult = serverStatusDataSource.get()

        // then
        assertEquals(expectedResult, actualResult)

        verify(ordering = Ordering.SEQUENCE) {
            clockMock.now()
        }
    }

    @Test
    fun `'get()' should return server status stopped when it has not been stopped`() {
        // given
        val expectedResult = ServerStatusDataModel.Stopped

        every { clockMock.now() } returns serverStartTimestamp
        serverStatusDataSource.setStarted()
        serverStatusDataSource.setStopped()

        // when
        val actualResult = serverStatusDataSource.get()

        // then
        assertEquals(expectedResult, actualResult)

        verify(ordering = Ordering.SEQUENCE) {
            clockMock.now()
        }
    }

    // endregion

}