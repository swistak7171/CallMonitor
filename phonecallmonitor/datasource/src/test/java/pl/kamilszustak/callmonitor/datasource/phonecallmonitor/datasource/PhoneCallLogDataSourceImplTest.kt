package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.phoneCallLogEntryDataModel
import kotlin.test.assertEquals

class PhoneCallLogDataSourceImplTest {

    // region Fields

    private val phoneCallLogDataSource: PhoneCallLogDataSourceImpl = PhoneCallLogDataSourceImpl()

    // endregion

    // region Tests

    @Test
    fun `'getAll()' should return an empty list when no phone call log entry has been added`() {
        // given
        val expectedResult = emptyList<PhoneCallLogEntryDataModel>()

        // when
        val actualResult = phoneCallLogDataSource.getAll()

        // then
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `'getAll()' should return a list containing a phone call log entry after it has been added`() {
        // given
        val expectedResult = listOf(phoneCallLogEntryDataModel)
        phoneCallLogDataSource.add(phoneCallLogEntryDataModel)

        // when
        val actualResult = phoneCallLogDataSource.getAll()

        // then
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `'getAllRx()' should emit an empty list when no phone call log entry has been added`() =
        runTest {
            // given
            val expectedResult = emptyList<PhoneCallLogEntryDataModel>()

            // when
            phoneCallLogDataSource.getAllRx()
                .test {
                    // then
                    assertEquals(expectedResult, awaitItem())
                    ensureAllEventsConsumed()
                }
        }

    @Test
    fun `'getAllRx()' should emit a list containing a phone call log entry after it has been added`() =
        runTest {
            // given
            val expectedResult = listOf(phoneCallLogEntryDataModel)
            phoneCallLogDataSource.add(phoneCallLogEntryDataModel)

            // when
            phoneCallLogDataSource.getAllRx()
                .test {
                    // then
                    assertEquals(expectedResult, awaitItem())
                    ensureAllEventsConsumed()
                }
        }

    @Test
    fun `'getAllRx()' should initially emit an empty list and then a list containing a phone call log entry after it has been added`() =
        runTest {
            // when
            phoneCallLogDataSource.getAllRx()
                .test {
                    // then
                    assertEquals(emptyList(), awaitItem())
                    ensureAllEventsConsumed()

                    // when
                    phoneCallLogDataSource.add(phoneCallLogEntryDataModel)
                    // then
                    assertEquals(listOf(phoneCallLogEntryDataModel), awaitItem())
                    ensureAllEventsConsumed()
                }
        }

    // endregion

}