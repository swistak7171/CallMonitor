package pl.kamilszustak.callmonitor.datasource

import pl.kamilszustak.callmonitor.model.PhoneCallLogEntry

interface PhoneCallLogDataSource {
    fun add(entry: PhoneCallLogEntry)
    fun getAll(): List<PhoneCallLogEntry>
}