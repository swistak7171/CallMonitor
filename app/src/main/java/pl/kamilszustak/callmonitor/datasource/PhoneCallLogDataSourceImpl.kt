package pl.kamilszustak.callmonitor.datasource

import pl.kamilszustak.callmonitor.model.PhoneCallLogEntry

class PhoneCallLogDataSourceImpl : PhoneCallLogDataSource {

    private val lock: Any = Any()
    private val calls: MutableList<PhoneCallLogEntry> = mutableListOf()

    override fun add(entry: PhoneCallLogEntry) {
        synchronized(lock) {
            calls.add(entry)
        }
    }

    override fun getAll(): List<PhoneCallLogEntry> {
        return calls
    }

}