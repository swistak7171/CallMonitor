package pl.kamilszustak.callmonitor.server.phonecallmonitor.mapper

import org.junit.Test
import pl.kamilszustak.callmonitor.server.mapper.toRemoteResponseModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.ongoingPhoneCallDomainModel
import pl.kamilszustak.callmonitor.server.phonecallmonitor.ongoingPhoneCallRemoteResponseModel
import kotlin.test.assertEquals

class OngoingPhoneCallDomainToRemoteMapperTest {

    // region Tests

    @Test
    fun `'toRemoteResponseModel()' should return remote response representation of ongoing phone call domain model`() {
        // when
        val actualResult = ongoingPhoneCallDomainModel.toRemoteResponseModel()

        // then
        assertEquals(ongoingPhoneCallRemoteResponseModel, actualResult)
    }

    // endregion

}