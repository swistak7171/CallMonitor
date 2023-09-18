package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel

/**
 * A data source for managing the server current status.
 */
interface ServerStatusDataSource {

    /**
     * Sets the server current status as started.
     */
    suspend fun setStarted()

    /**
     * Sets the server current status as stopped.
     */
    suspend fun setStopped()

    /**
     * Returns the server current status represented by [ServerStatusDataModel].
     */
    suspend fun get(): ServerStatusDataModel
}