package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

import kotlinx.datetime.Instant
import org.junit.Test
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

class PhoneCallLogEntryDomainModelTest {

    // region Tests

    @Test
    fun `'duration' should return a duration representing a time between the start and the end of the phone call`() {
        // given
        val logEntry = PhoneCallLogEntryDomainModel(
            id = UUID.randomUUID(),
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