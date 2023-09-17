package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import android.annotation.SuppressLint
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallMetadataDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel

internal class PhoneCallMetadataDataSourceImpl : PhoneCallMetadataDataSource {

    // region Fields

    private val phoneCallMetadata: MutableMap<String, PhoneCallMetadataDataModel> = mutableMapOf()

    // endregion

    // region PhoneCallMetadataDataSource Implementation

    override suspend fun get(id: String): PhoneCallMetadataDataModel {
        return phoneCallMetadata.getOrPut(id) {
            PhoneCallMetadataDataModel(id)
        }
    }

    @SuppressLint("NewApi")
    override suspend fun incrementTimesQueried(id: String) {
        phoneCallMetadata.compute(id) { _, value ->
            (value ?: PhoneCallMetadataDataModel(id))
                .let { metadata ->
                    metadata.copy(
                        timesQueried = metadata.timesQueried + 1
                    )
                }
        }
    }

    // endregion

}