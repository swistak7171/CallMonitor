package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel

interface PhoneCallMetadataDataSource {
    suspend fun get(id: String): PhoneCallMetadataDataModel
    suspend fun incrementTimesQueried(id: String)
}