package pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel

interface OngoingPhoneCallDataSource {
    fun get(): OngoingPhoneCallDataModel?
    fun getRx(): Flow<OngoingPhoneCallDataModel?>
    fun setStarted(model: OngoingPhoneCallDataModel)
    fun setEnded()
}