package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import io.mockk.Ordering
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Test
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.network.getAllInetAddresses
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.serverConfigurationDataModel
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.serverConfigurationDataModelWithDefaultHost
import pl.kamilszustak.callmonitor.datasource.phonecallmonitor.serverHost
import java.net.InetAddress
import kotlin.test.assertEquals

class ServerConfigurationDataSourceImplTest {

    // region Fields

    private val serverConfigurationDataSource: ServerConfigurationDataSourceImpl =
        ServerConfigurationDataSourceImpl()

    // endregion

    // region Tests

    @Test
    fun `'get()' should return server configuration`() {
        mockkStatic(::getAllInetAddresses) {
            // given
            val inetAddressMock = mockk<InetAddress>()

            every { getAllInetAddresses() } returns listOf(inetAddressMock)
            every { inetAddressMock.isLoopbackAddress } returns false
            every { inetAddressMock.isSiteLocalAddress } returns true
            every { inetAddressMock.hostAddress } returns serverHost

            // when
            val actualResult = serverConfigurationDataSource.get()

            // then
            assertEquals(serverConfigurationDataModel, actualResult)

            verify(ordering = Ordering.SEQUENCE) {
                getAllInetAddresses()
                inetAddressMock.isLoopbackAddress
                inetAddressMock.isSiteLocalAddress
                inetAddressMock.hostAddress
            }
            confirmVerified(inetAddressMock)
        }
    }

    @Test
    fun `'get()' should return server configuration with default host when network addresses collection is empty`() {
        mockkStatic(::getAllInetAddresses) {
            // given
            every { getAllInetAddresses() } returns emptyList()

            // when
            val actualResult = serverConfigurationDataSource.get()

            // then
            assertEquals(serverConfigurationDataModelWithDefaultHost, actualResult)

            verify(ordering = Ordering.SEQUENCE) {
                getAllInetAddresses()
            }
        }
    }

    @Test
    fun `'get()' should return server configuration with default host when available network address is loopback address`() {
        mockkStatic(::getAllInetAddresses) {
            // given
            val inetAddressMock = mockk<InetAddress>()

            every { getAllInetAddresses() } returns listOf(inetAddressMock)
            every { inetAddressMock.isLoopbackAddress } returns true

            // when
            val actualResult = serverConfigurationDataSource.get()

            // then
            assertEquals(serverConfigurationDataModelWithDefaultHost, actualResult)

            verify(ordering = Ordering.SEQUENCE) {
                getAllInetAddresses()
                inetAddressMock.isLoopbackAddress
            }
            confirmVerified(inetAddressMock)
        }
    }

    @Test
    fun `'get()' should return server configuration with default host when available network address is not site local address`() {
        mockkStatic(::getAllInetAddresses) {
            // given
            val inetAddressMock = mockk<InetAddress>()

            every { getAllInetAddresses() } returns listOf(inetAddressMock)
            every { inetAddressMock.isLoopbackAddress } returns false
            every { inetAddressMock.isSiteLocalAddress } returns false

            // when
            val actualResult = serverConfigurationDataSource.get()

            // then
            assertEquals(serverConfigurationDataModelWithDefaultHost, actualResult)

            verify(ordering = Ordering.SEQUENCE) {
                getAllInetAddresses()
                inetAddressMock.isLoopbackAddress
                inetAddressMock.isSiteLocalAddress
            }
            confirmVerified(inetAddressMock)
        }
    }

    @Test
    fun `'get()' should return server configuration with default host when available network address does not return host address`() {
        mockkStatic(::getAllInetAddresses) {
            // given
            val inetAddressMock = mockk<InetAddress>()

            every { getAllInetAddresses() } returns listOf(inetAddressMock)
            every { inetAddressMock.isLoopbackAddress } returns false
            every { inetAddressMock.isSiteLocalAddress } returns true
            every { inetAddressMock.hostAddress } returns null

            // when
            val actualResult = serverConfigurationDataSource.get()

            // then
            assertEquals(serverConfigurationDataModelWithDefaultHost, actualResult)

            verify(ordering = Ordering.SEQUENCE) {
                getAllInetAddresses()
                inetAddressMock.isLoopbackAddress
                inetAddressMock.isSiteLocalAddress
                inetAddressMock.hostAddress
            }
            confirmVerified(inetAddressMock)
        }
    }

    @Test
    fun `'get()' should return server configuration with default host when available network address returns invalid host address`() {
        mockkStatic(::getAllInetAddresses) {
            // given
            val inetAddressMock = mockk<InetAddress>()

            every { getAllInetAddresses() } returns listOf(inetAddressMock)
            every { inetAddressMock.isLoopbackAddress } returns false
            every { inetAddressMock.isSiteLocalAddress } returns true
            every { inetAddressMock.hostAddress } returns "   "

            // when
            val actualResult = serverConfigurationDataSource.get()

            // then
            assertEquals(serverConfigurationDataModelWithDefaultHost, actualResult)

            verify(ordering = Ordering.SEQUENCE) {
                getAllInetAddresses()
                inetAddressMock.isLoopbackAddress
                inetAddressMock.isSiteLocalAddress
                inetAddressMock.hostAddress
            }
            confirmVerified(inetAddressMock)
        }
    }


    // endregion

}