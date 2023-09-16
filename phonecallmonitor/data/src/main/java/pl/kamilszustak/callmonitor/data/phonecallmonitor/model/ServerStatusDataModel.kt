package pl.kamilszustak.callmonitor.data.phonecallmonitor.model

import kotlinx.datetime.Instant

sealed interface ServerStatusDataModel {

    data class Running(
        val startTimestamp: Instant,
    ) : ServerStatusDataModel

    data object Stopped : ServerStatusDataModel

}