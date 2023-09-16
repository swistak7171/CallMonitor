package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.ongoingPhoneCallDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallStartEventDomainModel
import kotlin.test.assertEquals

class PhoneCallStateDomainToOngoingPhoneCallDataMapperTest {

    // region Tests

    @Test
    fun `'toOngoingPhoneCallDataModel()' should return a data ongoing phone call representation of phone call start event domain model`() {
        // when
        val actualResult = phoneCallStartEventDomainModel.toOngoingPhoneCallDataModel()

        // then
        assertEquals(ongoingPhoneCallDataModel, actualResult)
    }

    // endregion

}