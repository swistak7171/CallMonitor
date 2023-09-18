package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.network

import java.net.InetAddress
import java.net.NetworkInterface

/**
 * Returns a list of all [InetAddress] objects that are associated with the available network
 * interfaces. If there are no available network interfaces or an [Exception] is thrown, an empty
 * list is returned.
 */
internal fun getAllInetAddresses(): List<InetAddress> {
    return try {
        NetworkInterface.getNetworkInterfaces()
            .toList()
            .map { it.inetAddresses.toList() }
            .flatten()
    } catch (exception: Exception) {
        emptyList()
    }
}