package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import android.annotation.SuppressLint
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallMetadataDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel

/**
 * A data source for managing phone call metadata.
 */
internal class PhoneCallMetadataDataSourceImpl : PhoneCallMetadataDataSource {

    // region Fields

    private val mutex: Mutex = Mutex()
    private val phoneCallMetadata: MutableMap<String, PhoneCallMetadataDataModel> = mutableMapOf()

    // endregion

    // region PhoneCallMetadataDataSource Implementation

    override suspend fun get(id: String): PhoneCallMetadataDataModel {
        return mutex.withLock {
            phoneCallMetadata.getOrPut(id) {
                // Create a new instance if it has not been stored yet.
                PhoneCallMetadataDataModel(id)
            }
        }
    }

    @SuppressLint("NewApi")
    override suspend fun incrementTimesQueried(id: String) {
        mutex.withLock {
            phoneCallMetadata.compute(id) { _, value ->
                // Create a new instance if it has not been stored yet.
                (value ?: PhoneCallMetadataDataModel(id))
                    .let { metadata ->
                        metadata.copy(
                            timesQueried = metadata.timesQueried + 1
                        )
                    }
            }
        }
    }

    // endregion

}