package pl.kamilszustak.callmonitor.server.phonecallmonitor.mapper

import org.junit.Test
import pl.kamilszustak.callmonitor.server.mapper.toRemoteResponseModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.phoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.phoneCallLogEntryRemoteResponseModel
import kotlin.test.assertEquals

class PhoneCallLogEntryDomainToRemoteMapperTest {

    // region Tests

    @Test
    fun `'toRemoteResponseModel()' should return remote response representation of phone call log entry domain model`() {
        // when
        val actualResult = phoneCallLogEntryDomainModel.toRemoteResponseModel()

        // then
        assertEquals(phoneCallLogEntryRemoteResponseModel, actualResult)
    }

    // endregion

}