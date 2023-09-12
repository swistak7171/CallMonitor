package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import pl.kamilszustak.callmonitor.model.OngoingPhoneCallDataModel

interface OngoingPhoneCallDataSource {
    fun get(): OngoingPhoneCallDataModel?
    fun getRx(): Flow<OngoingPhoneCallDataModel?>
    fun setStarted(model: OngoingPhoneCallDataModel)
    fun setEnded()
}