package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.ongoingPhoneCallDataModel
import kotlin.test.assertEquals
import kotlin.test.assertNull

class OngoingPhoneCallDataSourceImplTest {

    // region Fields

    private val ongoingPhoneCallDataSource: OngoingPhoneCallDataSource =
        OngoingPhoneCallDataSourceImpl()

    // endregion

    // region Tests

    @Test
    fun `'get()' should return 'null' when there is no ongoing phone call`() {
        // when
        val actualResult = ongoingPhoneCallDataSource.get()

        // then
        assertNull(actualResult)
    }

    @Test
    fun `'get()' should return ongoing phone call when it has been started`() {
        // given
        ongoingPhoneCallDataSource.setStarted(ongoingPhoneCallDataModel)

        // when
        val actualResult = ongoingPhoneCallDataSource.get()

        // then
        assertEquals(ongoingPhoneCallDataModel, actualResult)
    }

    @Test
    fun `'get()' should return 'null' when phone call has been ended`() {
        // given
        ongoingPhoneCallDataSource.setEnded()

        // when
        val actualResult = ongoingPhoneCallDataSource.get()

        // then
        assertNull(actualResult)
    }

    // endregion

}