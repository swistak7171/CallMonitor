package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

interface OngoingPhoneCallDataSource {
    fun get(): Pair<String, Instant>?
    fun getRx(): Flow<Pair<String, Instant>?>
    fun setStarted(phoneNumber: String, timestamp: Instant)
    fun setEnded()
}