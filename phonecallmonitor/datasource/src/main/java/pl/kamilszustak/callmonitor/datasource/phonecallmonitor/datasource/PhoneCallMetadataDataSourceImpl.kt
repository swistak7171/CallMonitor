package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import android.annotation.SuppressLint
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallMetadataDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel

internal class PhoneCallMetadataDataSourceImpl : PhoneCallMetadataDataSource {

    // region Fields

    private val mutex: Mutex = Mutex()
    private val phoneCallMetadata: MutableMap<String, PhoneCallMetadataDataModel> = mutableMapOf()

    // endregion

    // region PhoneCallMetadataDataSource Implementation

    override suspend fun get(id: String): PhoneCallMetadataDataModel {
        return mutex.withLock {
            phoneCallMetadata.getOrPut(id) {
                PhoneCallMetadataDataModel(id)
            }
        }
    }

    @SuppressLint("NewApi")
    override suspend fun incrementTimesQueried(id: String) {
        mutex.withLock {
            phoneCallMetadata.compute(id) { _, value ->
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