package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import io.mockk.Ordering
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.contactName
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallLogEntryDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallLogEntryDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallMetadataDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.phoneCallMetadataDomainModel
import kotlin.test.assertEquals

class PhoneCallLogEntryDataToDomainMapperTest {

    // region Tests

    @Test
    fun `'toDomainModel()' should return a domain representation of phone call log entry data model`() {
        mockkStatic(PhoneCallMetadataDataModel::toDomainModel) {
            // given
            every {
                phoneCallMetadataDataModel.toDomainModel()
            } returns phoneCallMetadataDomainModel

            // when
            val actualResult = phoneCallLogEntryDataModel.toDomainModel(
                metadata = phoneCallMetadataDataModel,
                contactName = contactName
            )

            // then
            assertEquals(phoneCallLogEntryDomainModel, actualResult)
            verify(ordering = Ordering.SEQUENCE) {
                phoneCallMetadataDataModel.toDomainModel()
            }
        }
    }

    // endregion

}