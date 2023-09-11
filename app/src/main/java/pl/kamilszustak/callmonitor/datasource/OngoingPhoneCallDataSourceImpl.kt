package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Instant

class OngoingPhoneCallDataSourceImpl : OngoingPhoneCallDataSource {

    private val callFlow: MutableStateFlow<Pair<String, Instant>?> = MutableStateFlow(null)

    override fun get(): Pair<String, Instant>? {
        return callFlow.value
    }

    override fun getRx(): Flow<Pair<String, Instant>?> {
        return callFlow
    }

    override fun setStarted(phoneNumber: String, timestamp: Instant) {
        callFlow.update { phoneNumber to timestamp }
    }

    override fun setEnded() {
        callFlow.update { null }
    }

}