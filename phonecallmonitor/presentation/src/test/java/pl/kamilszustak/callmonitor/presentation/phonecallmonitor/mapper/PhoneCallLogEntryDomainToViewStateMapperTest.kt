package pl.kamilszustak.callmonitor.presentation.phonecallmonitor.mapper

import org.junit.Test
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.phoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.phoneCallLogEntryViewState
import kotlin.test.assertEquals

class PhoneCallLogEntryDomainToViewStateMapperTest {

    // region Tests

    @Test
    fun `'toViewState()' should return view state representation of phone call log entry`() {
        // when
        val actualResult = phoneCallLogEntryDomainModel.toViewState()

        // then
        assertEquals(phoneCallLogEntryViewState, actualResult)
    }

    // endregion

}