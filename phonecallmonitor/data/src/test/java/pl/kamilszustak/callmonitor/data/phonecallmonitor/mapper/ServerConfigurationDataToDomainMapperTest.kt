package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.serverConfigurationDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.serverConfigurationDomainModel
import kotlin.test.assertEquals

class ServerConfigurationDataToDomainMapperTest {

    // region Tests

    @Test
    fun `'toDomainModel()' should return a domain representation of a server configuration data model`() {
        // when
        val actualResult = serverConfigurationDataModel.toDomainModel()

        // then
        assertEquals(serverConfigurationDomainModel, actualResult)
    }

    // endregion

}