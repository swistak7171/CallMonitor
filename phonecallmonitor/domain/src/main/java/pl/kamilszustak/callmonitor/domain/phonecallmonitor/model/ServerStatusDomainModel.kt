package pl.kamilszustak.callmonitor.domain.phonecallmonitor.model

import kotlinx.datetime.Instant

sealed interface ServerStatusDomainModel {

    data class Running(
        val startTimestamp: Instant,
    ) : ServerStatusDomainModel

    data object Stopped : ServerStatusDomainModel

}