package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.network

import io.mockk.Ordering
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Test
import java.net.InetAddress
import java.net.NetworkInterface
import kotlin.test.assertEquals

class GetAllInetAddressesTest {

    // region Tests

    @Test
    fun `'getAllInetAddresses()' should return an empty list of addresses when getting them throws an exception`() {
        mockkStatic(NetworkInterface::getNetworkInterfaces) {
            // given
            val expectedResult = emptyList<InetAddress>()
            every { NetworkInterface.getNetworkInterfaces() } throws Exception()

            // when
            val actualResult = getAllInetAddresses()

            // then
            assertEquals(expectedResult, actualResult)

            verify(ordering = Ordering.SEQUENCE) {
                NetworkInterface.getNetworkInterfaces()
            }
        }
    }

    // endregion

}