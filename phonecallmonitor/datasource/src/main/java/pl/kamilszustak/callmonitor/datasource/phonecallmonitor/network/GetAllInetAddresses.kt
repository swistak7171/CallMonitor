package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.network

import java.net.InetAddress
import java.net.NetworkInterface

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