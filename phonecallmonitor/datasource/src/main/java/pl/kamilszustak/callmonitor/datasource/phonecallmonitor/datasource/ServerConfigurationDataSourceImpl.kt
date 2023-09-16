package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerConfigurationDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerConfigurationDataModel
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException

private const val PORT: Int = 8080
private const val DEFAULT_HOST: String = "0.0.0.0"

class ServerConfigurationDataSourceImpl : ServerConfigurationDataSource {

    override fun get(): ServerConfigurationDataModel {
        return ServerConfigurationDataModel(
            host = getHost(),
            port = PORT
        )
    }

    private fun getHost(): String {
        val networkInterfaces = try {
            NetworkInterface.getNetworkInterfaces()
        } catch (exception: SocketException) {
            return DEFAULT_HOST
        }

        while (networkInterfaces.hasMoreElements()) {
            val networkInterface = networkInterfaces.nextElement()
            val inetAddresses = networkInterface.inetAddresses
            while (inetAddresses.hasMoreElements()) {
                val inetAddress = inetAddresses.nextElement()
                if (!inetAddress.isLoopbackAddress && inetAddress is InetAddress && inetAddress.isSiteLocalAddress) {
                    return inetAddress.hostAddress ?: DEFAULT_HOST
                }
            }
        }

        return DEFAULT_HOST
    }

}