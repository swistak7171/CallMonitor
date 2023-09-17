package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

import org.junit.Test
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.duration
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.phoneCallLogEntryDomainModel
import kotlin.test.assertEquals

class PhoneCallLogEntryDomainModelTest {

    // region Tests

    @Test
    fun `'duration' should return a duration representing a time between the start and the end of the phone call`() {
        // when
        val actualResult = phoneCallLogEntryDomainModel.duration

        // then
        assertEquals(duration, actualResult)
    }

    // endregion

}