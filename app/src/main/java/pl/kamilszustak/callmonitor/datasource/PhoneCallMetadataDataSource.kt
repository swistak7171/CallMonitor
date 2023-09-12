package pl.kamilszustak.callmonitor.datasource

import pl.kamilszustak.callmonitor.model.PhoneCallMetadataDataModel
import java.util.UUID

interface PhoneCallMetadataDataSource {
    suspend fun getOrCreate(id: UUID): PhoneCallMetadataDataModel
    suspend fun incrementTimesQueried(id: UUID)
}