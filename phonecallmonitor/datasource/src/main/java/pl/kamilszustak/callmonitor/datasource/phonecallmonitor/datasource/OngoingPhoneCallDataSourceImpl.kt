package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel

/**
 * A memory data source for managing ongoing phone calls.
 */
internal class OngoingPhoneCallDataSourceImpl : OngoingPhoneCallDataSource {

    // region Fields

    private val mutex: Mutex = Mutex()
    private var ongoingPhoneCall: OngoingPhoneCallDataModel? = null

    // endregion

    // region OngoingPhoneCallDataSource Implementation

    override suspend fun get(): OngoingPhoneCallDataModel? {
        return mutex.withLock {
            ongoingPhoneCall
        }
    }

    override suspend fun setStarted(model: OngoingPhoneCallDataModel) {
        mutex.withLock {
            ongoingPhoneCall = model
        }
    }

    override suspend fun setEnded() {
        mutex.withLock {
            ongoingPhoneCall = null
        }
    }

    // endregion

}