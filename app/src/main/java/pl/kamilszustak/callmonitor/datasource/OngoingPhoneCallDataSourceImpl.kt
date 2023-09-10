package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class OngoingPhoneCallDataSourceImpl : OngoingPhoneCallDataSource {

    private val callFlow: MutableStateFlow<String?> = MutableStateFlow(null)

    override fun get(): String? {
        return callFlow.value
    }

    override fun getRx(): Flow<String?> {
        return callFlow
    }

    override fun setStarted(phoneNumber: String) {
        callFlow.update { phoneNumber }
    }

    override fun setEnded() {
        callFlow.update { null }
    }

}