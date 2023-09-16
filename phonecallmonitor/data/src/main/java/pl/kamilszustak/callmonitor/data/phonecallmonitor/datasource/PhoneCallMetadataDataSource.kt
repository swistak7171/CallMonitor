package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel
import java.util.UUID

interface PhoneCallMetadataDataSource {
    suspend fun get(id: UUID): PhoneCallMetadataDataModel
    suspend fun incrementTimesQueried(id: UUID)
}