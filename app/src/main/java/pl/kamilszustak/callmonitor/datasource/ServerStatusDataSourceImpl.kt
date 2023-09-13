package pl.kamilszustak.callmonitor.datasource

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.model.ServerStatusDataModel

class ServerStatusDataSourceImpl(
    private val clock: Clock,
) : ServerStatusDataSource {

    private var startTimestamp: Instant? = null

    override fun setStarted() {
        startTimestamp = clock.now()
    }

    override fun setStopped() {
        startTimestamp = null
    }

    override fun get(): ServerStatusDataModel {
        val timestamp = startTimestamp

        return if (timestamp != null) {
            ServerStatusDataModel.Running(
                startTimestamp = timestamp
            )
        } else {
            ServerStatusDataModel.Stopped
        }
    }

}