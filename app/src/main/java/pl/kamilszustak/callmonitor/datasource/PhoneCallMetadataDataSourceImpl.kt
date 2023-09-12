package pl.kamilszustak.callmonitor.datasource

import android.annotation.SuppressLint
import pl.kamilszustak.callmonitor.model.PhoneCallMetadataDataModel
import java.util.UUID

class PhoneCallMetadataDataSourceImpl : PhoneCallMetadataDataSource {

    private val metadata: MutableMap<UUID, PhoneCallMetadataDataModel> = mutableMapOf()

    override suspend fun getOrCreate(id: UUID): PhoneCallMetadataDataModel {
        return metadata.getOrPut(id) {
            PhoneCallMetadataDataModel(id)
        }
    }

    @SuppressLint("NewApi")
    override suspend fun incrementTimesQueried(id: UUID) {
        metadata.computeIfPresent(id) { _, metadata ->
            metadata.copy(
                timesQueried = metadata.timesQueried + 1
            )
        }
    }
}