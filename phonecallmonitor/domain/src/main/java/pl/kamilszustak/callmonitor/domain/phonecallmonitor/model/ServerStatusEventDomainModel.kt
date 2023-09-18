package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

/**
 * A model representing a change of the server status. It can be either [Started] or [Stopped].
 */
sealed interface ServerStatusEventDomainModel {

    /**
     * A model representing a change of the server status when it has been started.
     */
    data object Started : ServerStatusEventDomainModel

    /**
     * A model representing a change of the server status when it has been stopped.
     */
    data object Stopped : ServerStatusEventDomainModel
}