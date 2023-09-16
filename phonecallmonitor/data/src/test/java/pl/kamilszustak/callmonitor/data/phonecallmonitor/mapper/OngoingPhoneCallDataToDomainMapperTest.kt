package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.contactName
import pl.kamilszustak.callmonitor.data.phonecallmonitor.ongoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.ongoingPhoneCallDomainModel
import kotlin.test.assertEquals

class OngoingPhoneCallDataToDomainMapperTest {

    // region Tests

    @Test
    fun `'toDomainModel()' should return a domain representation of ongoing phone call data model`() {
        // when
        val actualResult = ongoingPhoneCallDataModel.toDomainModel(contactName)

        // then
        assertEquals(ongoingPhoneCallDomainModel, actualResult)
    }

    // endregion

}