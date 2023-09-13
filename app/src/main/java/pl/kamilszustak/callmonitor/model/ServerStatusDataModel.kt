package pl.kamilszustak.callmonitor.model

import kotlinx.datetime.Instant

sealed interface ServerStatusDataModel {

    @JvmInline
    value class Running(
        val startTimestamp: Instant,
    ) : ServerStatusDataModel

    data object Stopped : ServerStatusDataModel

}