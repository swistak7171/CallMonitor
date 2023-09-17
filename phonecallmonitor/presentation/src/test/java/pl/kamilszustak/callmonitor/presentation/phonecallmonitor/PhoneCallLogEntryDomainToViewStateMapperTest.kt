package pl.kamilszustak.callmonitor.presentation.phonecallmonitor

import org.junit.Test
import pl.kamilszustak.callmonitor.presentation.phonecallmonitor.mapper.toViewState
import kotlin.test.assertEquals

class PhoneCallLogEntryDomainToViewStateMapperTest {

    // region Tests

    @Test
    fun `'toViewState()' should return view state with contact name when available`() {
        // when
        val actualResult = phoneCallLogEntryDomainModel.toViewState()

        // then
        assertEquals(phoneCallLogEntryViewState, actualResult)
    }

    @Test
    fun `'toViewState()' should return view state with phone number when contact name is unavailable`() {
        // when
        val actualResult = phoneCallLogEntryDomainModelWithoutContactName.toViewState()

        // then
        assertEquals(phoneCallLogEntryViewStateWithPhoneNumber, actualResult)
    }

    // endregion

}