package pl.kamilszustak.callmonitor.datasource

import kotlinx.coroutines.flow.Flow

interface OngoingPhoneCallDataSource {
    fun get(): String?
    fun getRx(): Flow<String?>
    fun setStarted(phoneNumber: String)
    fun setEnded()
}