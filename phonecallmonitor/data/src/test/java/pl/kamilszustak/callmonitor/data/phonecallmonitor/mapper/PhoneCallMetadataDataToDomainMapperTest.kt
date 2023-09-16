package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallMetadataDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallMetadataDomainModel
import kotlin.test.assertEquals

class PhoneCallMetadataDataToDomainMapperTest {

    // region Tests

    @Test
    fun `'toDomainModel()' should return a domain representation of phone call metadata data model`() {
        // when
        val actualResult = phoneCallMetadataDataModel.toDomainModel()

        // when
        assertEquals(phoneCallMetadataDomainModel, actualResult)
    }

    // endregion

}