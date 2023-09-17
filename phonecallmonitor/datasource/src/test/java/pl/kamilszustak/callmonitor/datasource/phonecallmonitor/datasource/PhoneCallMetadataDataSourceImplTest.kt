package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import kotlinx.coroutines.test.runTest
import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.id
import kotlin.test.assertEquals

class PhoneCallMetadataDataSourceImplTest {

    // region Fields

    private val phoneCallMetadataDataSource: PhoneCallMetadataDataSourceImpl =
        PhoneCallMetadataDataSourceImpl()

    // endregion

    // region Tests

    @Test
    fun `'get()' should return default phone call metadata when it has never been modified`() =
        runTest {
            // given
            val expectedResult = PhoneCallMetadataDataModel(id)

            // when
            val actualResult = phoneCallMetadataDataSource.get(id)

            // then
            assertEquals(expectedResult, actualResult)
        }

    @Test
    fun `'get()' should return phone call metadata with updated content when it has been modified`() =
        runTest {
            // given
            val expectedResult = PhoneCallMetadataDataModel(
                id = id,
                timesQueried = 1
            )
            phoneCallMetadataDataSource.incrementTimesQueried(id)

            // when
            val actualResult = phoneCallMetadataDataSource.get(id)

            // then
            assertEquals(expectedResult, actualResult)
        }

    // endregion

}