package pl.kamilszustak.callmonitor.datasource.phonecallmonitor.datasource

import android.annotation.SuppressLint
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.PhoneCallMetadataDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel
import java.util.UUID

class PhoneCallMetadataDataSourceImpl : PhoneCallMetadataDataSource {

    private val phoneCallMetadata: MutableMap<UUID, PhoneCallMetadataDataModel> = mutableMapOf()

    override suspend fun getOrCreate(id: UUID): PhoneCallMetadataDataModel {
        return phoneCallMetadata.getOrPut(id) {
            PhoneCallMetadataDataModel(id)
        }
    }

    @SuppressLint("NewApi")
    override suspend fun incrementTimesQueried(id: UUID) {
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