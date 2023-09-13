package pl.kamilszustak.callmonitor.model

import kotlinx.datetime.Instant

sealed interface ServerStatusDomainModel {

    @JvmInline
    value class Running(
        val startTimestamp: Instant,
    ) : ServerStatusDomainModel

    data object Stopped : ServerStatusDomainModel

}