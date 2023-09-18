package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel.Running
import pl.kamilszustak.callmonitor.domain.phonecallmonitor.model.ServerStatusDomainModel.Stopped

/**
 * A model representing the status of the server. It can be either [Running] or [Stopped].
 */
sealed interface ServerStatusDomainModel {

    /**
     * A model representing the status of the server when it is running. It contains information
     * about the [startTimestamp] of the server.
     */
    data class Running(
        val startTimestamp: Instant,
    ) : ServerStatusDomainModel

    /**
     * A model representing the status of the server when it is stopped.
     */
    data object Stopped : ServerStatusDomainModel
}