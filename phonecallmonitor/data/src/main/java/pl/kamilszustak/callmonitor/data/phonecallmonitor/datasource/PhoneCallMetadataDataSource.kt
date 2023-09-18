package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.PhoneCallMetadataDataModel

/**
 * A data source for managing phone call metadata.
 */
interface PhoneCallMetadataDataSource {

    /**
     * Returns phone call metadata represented by [PhoneCallMetadataDataModel] associated with the
     * specified [id].
     */
    suspend fun get(id: String): PhoneCallMetadataDataModel

    /**
     * Increments the number of times a phone call with the specified [id] has been queried.
     */
    suspend fun incrementTimesQueried(id: String)
}