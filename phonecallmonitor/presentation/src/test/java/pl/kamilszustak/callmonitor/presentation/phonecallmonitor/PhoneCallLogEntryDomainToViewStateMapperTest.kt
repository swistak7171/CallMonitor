package pl.kamilszustak.callmonitor.presentation.phonecallmonitor

import org.junit.Test
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.mapper.toViewState
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