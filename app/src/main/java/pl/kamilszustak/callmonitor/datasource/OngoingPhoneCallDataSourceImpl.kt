package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import pl.kamilszustak.callmonitor.data.phonecallmonitor.datasource.OngoingPhoneCallDataSource
import pl.kamilszustak.callmonitor.data.phonecallmonitor.model.OngoingPhoneCallDataModel

class OngoingPhoneCallDataSourceImpl : OngoingPhoneCallDataSource {

    private val ongoingPhoneCall: MutableStateFlow<OngoingPhoneCallDataModel?> =
        MutableStateFlow(null)

    override fun get(): OngoingPhoneCallDataModel? {
        return ongoingPhoneCall.value
    }

    override fun getRx(): Flow<OngoingPhoneCallDataModel?> {
        return ongoingPhoneCall
    }

    override fun setStarted(model: OngoingPhoneCallDataModel) {
        ongoingPhoneCall.update { model }
    }

    override fun setEnded() {
        ongoingPhoneCall.update { null }
    }

}