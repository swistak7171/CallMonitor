package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class OngoingPhoneCallDataSourceImpl : OngoingPhoneCallDataSource {

    private val callFlow: MutableStateFlow<Pair<String, Long>?> = MutableStateFlow(null)

    override fun get(): Pair<String, Long>? {
        return callFlow.value
    }

    override fun getRx(): Flow<Pair<String, Long>?> {
        return callFlow
    }

    override fun setStarted(phoneNumber: String, timestamp: Long) {
        callFlow.update { phoneNumber to timestamp }
    }

    override fun setEnded() {
        callFlow.update { null }
    }

}