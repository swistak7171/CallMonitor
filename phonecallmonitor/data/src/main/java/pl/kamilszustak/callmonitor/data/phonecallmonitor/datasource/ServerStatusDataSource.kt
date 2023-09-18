package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel

/**
 * A data source for managing the current server status.
 */
interface ServerStatusDataSource {

    /**
     * Sets the current server status as started.
     */
    suspend fun setStarted()

    /**
     * Sets the current server status as stopped.
     */
    suspend fun setStopped()

    /**
     * Returns the current server status represented by [ServerStatusDataModel].
     */
    suspend fun get(): ServerStatusDataModel
}