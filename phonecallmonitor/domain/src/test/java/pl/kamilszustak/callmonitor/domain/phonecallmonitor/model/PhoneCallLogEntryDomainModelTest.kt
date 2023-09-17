package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

import kotlinx.datetime.Instant
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

class PhoneCallLogEntryDomainModelTest {

    // region Tests

    @Test
    fun `'duration' should return a duration representing a time between the start and the end of the phone call`() {
        // given
        val logEntry = PhoneCallLogEntryDomainModel(
            id = "eee144a0-553c-11ee-8c99-0242ac120002",
            metadata = PhoneCallMetadataDomainModel(
                timesQueried = 0
            ),
            startTimestamp = Instant.fromEpochSeconds(10),
            endTimestamp = Instant.fromEpochSeconds(25),
            phoneNumber = "123123123",
            contactName = "John Smith"
        )
        val expectedResult = 15.seconds

        // when
        val actualResult = logEntry.duration

        // then
        assertEquals(expectedResult, actualResult)
    }

    // endregion

}