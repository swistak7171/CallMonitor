package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerStatusDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel

class ServerStatusDataSourceImpl(
    private val clock: Clock,
) : ServerStatusDataSource {

    // region Fields

    private var startTimestamp: Instant? = null

    // endregion

    // region ServerStatusDataSource implementation

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

    // endregion

}