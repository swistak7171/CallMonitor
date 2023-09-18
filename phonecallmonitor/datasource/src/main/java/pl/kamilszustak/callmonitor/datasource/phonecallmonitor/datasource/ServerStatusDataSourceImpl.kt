package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.ServerStatusDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.ServerStatusDataModel

/**
 * A data source for managing the current server status.
 */
internal class ServerStatusDataSourceImpl(
    /**
     * An instance of [Clock] used for timestamping the server status changes.
     */
    private val clock: Clock,
) : ServerStatusDataSource {

    // region Fields

    private val mutex: Mutex = Mutex()

    /**
     * The timestamp of the server last start.
     */
    private var startTimestamp: Instant? = null

    // endregion

    // region ServerStatusDataSource implementation

    override suspend fun setStarted() {
        mutex.withLock {
            startTimestamp = clock.now()
        }
    }

    override suspend fun setStopped() {
        mutex.withLock {
            startTimestamp = null
        }
    }

    override suspend fun get(): ServerStatusDataModel {
        return mutex.withLock {
            val timestamp = startTimestamp

            if (timestamp != null) {
                ServerStatusDataModel.Running(
                    startTimestamp = timestamp
                )
            } else {
                ServerStatusDataModel.Stopped
            }
        }
    }

    // endregion

}