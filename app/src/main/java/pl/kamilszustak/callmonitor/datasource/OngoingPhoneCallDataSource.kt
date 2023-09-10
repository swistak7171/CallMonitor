package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow

interface OngoingPhoneCallDataSource {
    fun get(): Pair<String, Long>?
    fun getRx(): Flow<Pair<String, Long>?>
    fun setStarted(phoneNumber: String, timestamp: Long)
    fun setEnded()
}