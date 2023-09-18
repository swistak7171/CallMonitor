package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel.Running
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel.Stopped

/**
 * A model representing the status of the server. It can be either [Running] or [Stopped].
 */
sealed interface ServerStatusDataModel {

    /**
     * A model representing the status of the server when it is running. It contains information
     * about the [startTimestamp] of the server.
     */
    data class Running(
        val startTimestamp: Instant,
    ) : ServerStatusDataModel

    /**
     * A model representing the status of the server when it is stopped.
     */
    data object Stopped : ServerStatusDataModel

}