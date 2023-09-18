package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel

/**
 * A data source for managing ongoing phone calls.
 */
interface OngoingPhoneCallDataSource {

    /**
     * Returns [OngoingPhoneCallDataModel] representing information about the ongoing phone call,
     * if there is one, or `null` if no ongoing call is available.
     */
    suspend fun get(): OngoingPhoneCallDataModel?

    /**
     * Sets the status of an ongoing phone call as started with [model] representing the details
     * of the started call.
     */
    suspend fun setStarted(model: OngoingPhoneCallDataModel)

    /**
     * Sets the status of an ongoing phone call as ended.
     */
    suspend fun setEnded()
}