package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel

interface OngoingPhoneCallDataSource {
    suspend fun get(): OngoingPhoneCallDataModel?
    suspend fun setStarted(model: OngoingPhoneCallDataModel)
    suspend fun setEnded()
}