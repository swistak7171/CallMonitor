package pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper

import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.serverStatusRunningDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.serverStatusRunningDomainModel
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel
import kotlin.test.assertEquals

class ServerStatusDataToDomainMapperTest {

    // region Tests

    @Test
    fun `'toDomainModel()' should return a domain representation of server status running data model`() {
        // when
        val actualResult = serverStatusRunningDataModel.toDomainModel()

        // then
        assertEquals(serverStatusRunningDomainModel, actualResult)
    }

    @Test
    fun `'toDomainModel()' should return a domain representation of server status stopped data model`() {
        // when
        val actualResult = ServerStatusDataModel.Stopped.toDomainModel()

        // then
        assertEquals(ServerStatusDomainModel.Stopped, actualResult)
    }

    // endregion

}