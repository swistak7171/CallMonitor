package pl.kamilszustak.callmonitor.data.phonecallmonitor.repository

import io.mockk.Ordering
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Test
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerConfigurationDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.mapper.toDomainModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerConfigurationDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.serverConfigurationDataModel
import pl.kamilszustak.callmonitor.data.phonecallmonitor.serverConfigurationDomainModel
import kotlin.test.assertEquals

class ServerConfigurationRepositoryImplTest {

    // region Fields

    private val serverConfigurationDataSourceMock: ServerConfigurationDataSource = mockk()

    private val serverConfigurationRepository: ServerConfigurationRepositoryImpl =
        ServerConfigurationRepositoryImpl(
            serverConfigurationDataSource = serverConfigurationDataSourceMock
        )

    // endregion

    // region Tests

    @Test
    fun `'get()' should return a server configuration`() {
        mockkStatic(ServerConfigurationDataModel::toDomainModel) {
            // given
            every { serverConfigurationDataSourceMock.get() } returns serverConfigurationDataModel
            every {
                serverConfigurationDataModel.toDomainModel()
            } returns serverConfigurationDomainModel

            // when
            val actualResult = serverConfigurationRepository.get()

            // then
            assertEquals(serverConfigurationDomainModel, actualResult)

            verify(ordering = Ordering.SEQUENCE) {
                serverConfigurationDataSourceMock.get()
                serverConfigurationDataModel.toDomainModel()
            }
            confirmVerified(serverConfigurationDataSourceMock)
        }
    }

    // endregion

}