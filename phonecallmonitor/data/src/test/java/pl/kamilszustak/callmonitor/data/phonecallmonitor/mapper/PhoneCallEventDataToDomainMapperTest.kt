package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallEndEventDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallEndEventDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallStartEventDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallStartEventDomainModel
import kotlin.test.assertEquals

class PhoneCallEventDataToDomainMapperTest {

    // region Tests

    @Test
    fun `'toDomainModel()' should return a domain representation of phone call start event data model`() {
        // when
        val actualResult = phoneCallStartEventDataModel.toDomainModel()

        // then
        assertEquals(phoneCallStartEventDomainModel, actualResult)
    }

    @Test
    fun `'toDomainModel()' should return a domain representation of phone call end event data model`() {
        // when
        val actualResult = phoneCallEndEventDataModel.toDomainModel()

        // then
        assertEquals(phoneCallEndEventDomainModel, actualResult)
    }

    // endregion

}