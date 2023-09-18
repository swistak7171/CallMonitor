package pl.kamilszustak.callmonitor.domain.phonecallmonitor.repository

import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel

/**
 * A repository for managing the server current status.
 */
interface ServerStatusRepository {

    /**
     * Sets the server current status as started.
     */
    suspend fun setStarted()

    /**
     * Sets the server current status as stopped.
     */
    suspend fun setStopped()

    /**
     * Returns the server current status represented by [ServerStatusDomainModel].
     */
    suspend fun get(): ServerStatusDomainModel
}